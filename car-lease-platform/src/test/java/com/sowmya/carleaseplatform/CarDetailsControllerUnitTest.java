package com.sowmya.carleaseplatform;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;
import com.sowmya.carleaseplatform.constants.CarLeaseServiceConstants;
import com.sowmya.carleaseplatform.controller.CarDetailsController;
import com.sowmya.carleaseplatform.dto.CarDetailsRequest;
import com.sowmya.carleaseplatform.dto.CarDetailsResponse;
import com.sowmya.carleaseplatform.dto.ResponseDto;
import com.sowmya.carleaseplatform.exception.CarAlreadyExistsException;
import com.sowmya.carleaseplatform.exception.DetailsNotFoundException;
import com.sowmya.carleaseplatform.service.CarDetailsService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CarDetailsController.class)
public class CarDetailsControllerUnitTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private CarDetailsService carDetailsService;


  private Gson gson = new Gson();


  @Test
  public void saveCarDetailsSuccessUnitTest() throws Exception {

    ResponseDto response = new ResponseDto();
    response = ResponseDto.builder().responseCode(CarLeaseServiceConstants.SUCCESS_CODE)
        .responseMessage(CarLeaseServiceConstants.NEW_CAR_SAVE_SUCCESS).build();
    when(carDetailsService.saveCarDetails(Mockito.any(CarDetailsRequest.class)))
        .thenReturn(response);

    CarDetailsRequest newCar = CarDetailsRequest.builder().carNumber("2-XXX-90").make("Toyota")
        .model("Camry").version("2").noOfDoors(4).co2Emission(125D).grossPrice(40000D)
        .interestRate(4F).netPrice(38000D).mileage(19F).userName("Sowmya").build();

    mvc.perform(post("/carDetails/saveCarDetails").contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(newCar))).andExpect(status().isOk())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.SUCCESS_CODE)))
        .andExpect(jsonPath("responseMessage", is(CarLeaseServiceConstants.NEW_CAR_SAVE_SUCCESS)));
  }

  @Test
  public void addDuplicateCarUnitTest() throws Exception {

    when(carDetailsService.saveCarDetails(Mockito.any(CarDetailsRequest.class)))
        .thenThrow(new CarAlreadyExistsException(CarLeaseServiceConstants.DUPLICATE_CAR_NAME));

    CarDetailsRequest newCar = CarDetailsRequest.builder().carNumber("2-XXX-90").make("Toyota")
        .model("Camry").version("2").noOfDoors(4).co2Emission(125D).interestRate(4F).grossPrice(40000D)
        .netPrice(38000D).mileage(19F).userName("Sowmya").build();

    mvc.perform(post("/carDetails/saveCarDetails").contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(newCar))).andExpect(status().isConflict())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.CONFLICT)))
        .andExpect(jsonPath("responseMessage", is(CarLeaseServiceConstants.DUPLICATE_CAR_NAME)));
  }

  @Test
  public void saveCarWithInvalidRequestUnitTest() throws Exception {

    ResponseDto response = new ResponseDto();
    response = ResponseDto.builder().responseCode(CarLeaseServiceConstants.SUCCESS_CODE)
        .responseMessage(CarLeaseServiceConstants.NEW_CAR_SAVE_SUCCESS).build();
    when(carDetailsService.saveCarDetails(Mockito.any(CarDetailsRequest.class)))
        .thenReturn(response);

    CarDetailsRequest newCar = CarDetailsRequest.builder().carNumber("2-XXX-900").model("Camry")
        .version("2").noOfDoors(4).co2Emission(125D).grossPrice(40000D).netPrice(38000D)
        .mileage(19F).userName("Sowmya").build();

    mvc.perform(post("/carDetails/saveCarDetails").contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(newCar))).andExpect(status().isBadRequest())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.BAD_REQUEST)));
  }

  @Test
  public void deleteCarUnitTest() throws Exception {

    ResponseDto response = new ResponseDto();
    response = ResponseDto.builder().responseCode(CarLeaseServiceConstants.SUCCESS_CODE)
        .responseMessage(CarLeaseServiceConstants.DELETE_CAR_SUCCESS).build();
    when(carDetailsService.deleteCar(Mockito.any(String.class))).thenReturn(response);


    mvc.perform(delete("/carDetails/deleteCar").contentType(MediaType.APPLICATION_JSON)
        .param("carNumber", "2-XXX-90")).andExpect(status().isOk())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.SUCCESS_CODE)))
        .andExpect(jsonPath("responseMessage", is(CarLeaseServiceConstants.DELETE_CAR_SUCCESS)));
  }

  @Test
  public void deleteUnavailableCarUnitTest() throws Exception {

    when(carDetailsService.deleteCar(Mockito.any(String.class))).thenThrow(new DetailsNotFoundException(CarLeaseServiceConstants.CAR_NOT_FOUND));


    mvc.perform(delete("/carDetails/deleteCar").contentType(MediaType.APPLICATION_JSON)
        .param("carNumber", "2-XXX-20")).andExpect(status().isNotFound())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.NOT_FOUND)))
        .andExpect(jsonPath("responseMessage", is(CarLeaseServiceConstants.CAR_NOT_FOUND)));
  }

  @Test
  public void deleteCarWithInvalidRequestUnitTest() throws Exception {

    ResponseDto response = new ResponseDto();
    response = ResponseDto.builder().responseCode(CarLeaseServiceConstants.SUCCESS_CODE)
        .responseMessage(CarLeaseServiceConstants.DELETE_CAR_SUCCESS).build();
    when(carDetailsService.deleteCar(Mockito.any(String.class))).thenReturn(response);


    mvc.perform(delete("/carDetails/deleteCar").contentType(MediaType.APPLICATION_JSON)
        .param("carNumber", "2-XXX")).andExpect(status().isBadRequest())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.BAD_REQUEST)));
  }

  @Test
  public void updateCarUnitTest() throws Exception {

    ResponseDto response = new ResponseDto();
    response = ResponseDto.builder().responseCode(CarLeaseServiceConstants.SUCCESS_CODE)
        .responseMessage(CarLeaseServiceConstants.UPDATE_CAR_SUCCESS).build();
    when(carDetailsService.updateCarDetailsByName(Mockito.any(CarDetailsRequest.class)))
        .thenReturn(response);

    CarDetailsRequest updateRequest = CarDetailsRequest.builder().carNumber("2-XXX-90")
        .make("Toyota").model("Camry").version("2").noOfDoors(4).co2Emission(125D).interestRate(4F)
        .grossPrice(40000D).netPrice(38000D).mileage(19F).userName("Sowmya").build();


    mvc.perform(patch("/carDetails/updateCarDetails").contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(updateRequest))).andExpect(status().isOk())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.SUCCESS_CODE)))
        .andExpect(jsonPath("responseMessage", is(CarLeaseServiceConstants.UPDATE_CAR_SUCCESS)));
  }

  @Test
  public void updateCarWithInvalidRequestUnitTest() throws Exception {

    ResponseDto response = new ResponseDto();
    response = ResponseDto.builder().responseCode(CarLeaseServiceConstants.SUCCESS_CODE)
        .responseMessage(CarLeaseServiceConstants.UPDATE_CAR_SUCCESS).build();
    when(carDetailsService.updateCarDetailsByName(Mockito.any(CarDetailsRequest.class)))
        .thenReturn(response);

    CarDetailsRequest updateRequest = CarDetailsRequest.builder().carNumber("2-XXX-90")
        .make("Toyota").model("Camry").co2Emission(125D).grossPrice(40000D).netPrice(38000D)
        .mileage(19F).userName("Sowmya").build();


    mvc.perform(patch("/carDetails/updateCarDetails").contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(updateRequest))).andExpect(status().isBadRequest())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.BAD_REQUEST)));
  }


  @Test
  public void updateUnavailableCarUnitTest() throws Exception {

    when(carDetailsService.updateCarDetailsByName(Mockito.any(CarDetailsRequest.class)))
        .thenThrow(new DetailsNotFoundException(CarLeaseServiceConstants.CAR_NOT_FOUND));

    CarDetailsRequest updateRequest = CarDetailsRequest.builder().carNumber("2-X9X-90")
        .make("Toyota").model("Camry").version("2").noOfDoors(4).co2Emission(125D).interestRate(4F)
        .grossPrice(40000D).netPrice(38000D).mileage(19F).userName("Sowmya").build();


    mvc.perform(patch("/carDetails/updateCarDetails").contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(updateRequest))).andExpect(status().isNotFound())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.NOT_FOUND)))
        .andExpect(jsonPath("responseMessage", is(CarLeaseServiceConstants.CAR_NOT_FOUND)));
  }

  @Test
  public void findCarSuccessUnitTest() throws Exception {

    CarDetailsResponse response = CarDetailsResponse.builder().carNumber("2-X9X-90").make("Toyota")
        .model("Cramy").version("2").noOfDoors(4).co2Emission(120D).grossPrice(40000D)
        .netPrice(38000D).mileage(20F).responseCode(CarLeaseServiceConstants.SUCCESS_CODE)
        .responseMessage(CarLeaseServiceConstants.FIND_CAR_SUCCESS).build();
    when(carDetailsService.findCarByCarNumber(Mockito.any(String.class))).thenReturn(response);


    mvc.perform(get("/carDetails/getCarDetails").contentType(MediaType.APPLICATION_JSON)
        .param("carNumber", "2-X9X-90")).andExpect(status().isOk())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.SUCCESS_CODE)))
        .andExpect(jsonPath("responseMessage", is(CarLeaseServiceConstants.FIND_CAR_SUCCESS)));
  }

  @Test
  public void findCarWithNoRequestUnitTest() throws Exception {

    CarDetailsResponse response = CarDetailsResponse.builder().carNumber("2-X9X-90").make("Toyota")
        .model("Cramy").version("2").noOfDoors(4).co2Emission(120D).grossPrice(40000D)
        .netPrice(38000D).mileage(20F).responseCode(CarLeaseServiceConstants.SUCCESS_CODE)
        .responseMessage(CarLeaseServiceConstants.FIND_CAR_SUCCESS).build();
    when(carDetailsService.findCarByCarNumber(Mockito.any(String.class))).thenReturn(response);

    mvc.perform(get("/carDetails/getCarDetails").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.BAD_REQUEST)));
  }

  @Test
  public void findUnavailableCarUnitTest() throws Exception {

    when(carDetailsService.findCarByCarNumber(Mockito.any(String.class))).thenThrow(new DetailsNotFoundException(CarLeaseServiceConstants.CAR_NOT_FOUND));


    mvc.perform(get("/carDetails/getCarDetails").contentType(MediaType.APPLICATION_JSON)
        .param("carNumber", "2-X9X-90")).andExpect(status().isNotFound())
        .andExpect(jsonPath("responseCode", is(CarLeaseServiceConstants.NOT_FOUND)));
  }

}


