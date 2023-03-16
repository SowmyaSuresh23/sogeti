package com.sowmya.carleaseplatform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.sowmya.carleaseplatform.constants.CarLeaseServiceConstants;
import com.sowmya.carleaseplatform.dto.CarDetailsRequest;
import com.sowmya.carleaseplatform.dto.ResponseDto;
import com.sowmya.carleaseplatform.entity.CarDetailsEntity;
import com.sowmya.carleaseplatform.exception.CarAlreadyExistsException;
import com.sowmya.carleaseplatform.exception.DetailsNotFoundException;
import com.sowmya.carleaseplatform.repository.CarDetailsRepository;
import com.sowmya.carleaseplatform.service.CarDetailsService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CarDetailsService.class)
public class CarDetailsServiceUnitTest {

  @MockBean
  private CarDetailsRepository carDetailsRepository;

  @Autowired
  private CarDetailsService carDetailsService;

  @Test
  public void saveCarSuccessTest() throws Exception { 
      
      when(carDetailsRepository.findByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(Optional.empty());
      when(carDetailsRepository.save(Mockito.any(CarDetailsEntity.class))).thenReturn(new CarDetailsEntity());
      
      CarDetailsRequest newCar = CarDetailsRequest.builder().carNumber("2-XXX-90").make("Toyota")
          .model("Camry").version("2").noOfDoors(4).co2Emission(125D).grossPrice(40000D)
          .netPrice(38000D).mileage(19F).userName("Sowmya").build();
      
      ResponseDto saveResponse = carDetailsService.saveCarDetails(newCar);
      assertThat(saveResponse.getResponseCode().equalsIgnoreCase(CarLeaseServiceConstants.SUCCESS_CODE));
      
  }

  @Test
  public void saveDuplicateCarTest() throws Exception { 
      when(carDetailsRepository.findByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(Optional.of(new CarDetailsEntity()));
      when(carDetailsRepository.save(Mockito.any(CarDetailsEntity.class))).thenReturn(new CarDetailsEntity());
      
      CarDetailsRequest newCar = CarDetailsRequest.builder().carNumber("2-XXX-90").make("Toyota")
          .model("Camry").version("2").noOfDoors(4).co2Emission(125D).grossPrice(40000D)
          .netPrice(38000D).mileage(19F).userName("Sowmya").build();
      
      assertThrows(CarAlreadyExistsException.class, () -> {
        carDetailsService.saveCarDetails(newCar);
      });
  }

  @Test
  public void deleteCarSuccessTest() throws Exception {
    when(carDetailsRepository.findByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(Optional.of(new CarDetailsEntity()));
      when(carDetailsRepository.deleteByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(1L);
      
      ResponseDto saveResponse = carDetailsService.deleteCar("2-XXX-90");
      assertThat(saveResponse.getResponseCode().equalsIgnoreCase(CarLeaseServiceConstants.SUCCESS_CODE));
      assertThat(saveResponse.getResponseMessage().equalsIgnoreCase(CarLeaseServiceConstants.DELETE_CAR_SUCCESS));
  }

  @Test
  public void deleteUnavailableCarTest() throws Exception {
    when(carDetailsRepository.findByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(Optional.empty());
      when(carDetailsRepository.deleteByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(0L);
      
      assertThrows(DetailsNotFoundException.class, () -> {
        carDetailsService.deleteCar("2-XXX-9000");
      });
  }

  @Test
  public void updateCarSuccessTest() throws Exception {
      when(carDetailsRepository.findByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(Optional.of(new CarDetailsEntity()));
      when(carDetailsRepository.save(Mockito.any(CarDetailsEntity.class))).thenReturn(new CarDetailsEntity());
      
      CarDetailsRequest updateRequest = CarDetailsRequest.builder().carNumber("2-XXX-90").make("Toyota")
          .model("Camry").version("2").noOfDoors(4).co2Emission(125D).grossPrice(40000D)
          .netPrice(38000D).mileage(19F).userName("Sowmya").build();
      
      ResponseDto response = carDetailsService.updateCarDetailsByName(updateRequest);
      assertThat(response.getResponseCode().equalsIgnoreCase(CarLeaseServiceConstants.SUCCESS_CODE));
      assertThat(response.getResponseMessage().equalsIgnoreCase(CarLeaseServiceConstants.UPDATE_CAR_SUCCESS));
  }

  @Test
  public void updateUnavailableCarTest() throws Exception {
      when(carDetailsRepository.findByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(Optional.empty());
      when(carDetailsRepository.save(Mockito.any(CarDetailsEntity.class))).thenReturn(new CarDetailsEntity());
      
      CarDetailsRequest updateRequest = CarDetailsRequest.builder().carNumber("2-XXX-90").make("Toyota")
          .model("Camry").version("2").noOfDoors(4).co2Emission(125D).grossPrice(40000D)
          .netPrice(38000D).mileage(19F).userName("Sowmya").build();
      
      assertThrows(DetailsNotFoundException.class, () -> {
        carDetailsService.updateCarDetailsByName(updateRequest);
    });
  }

  @Test
  public void findCarSuccessTest() throws Exception {
      when(carDetailsRepository.findByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(Optional.of(new CarDetailsEntity()));
      
      ResponseDto response = carDetailsService.findCarByCarNumber("2-XXX-90");
      assertThat(response.getResponseCode().equalsIgnoreCase(CarLeaseServiceConstants.SUCCESS_CODE));
      assertThat(response.getResponseMessage().equalsIgnoreCase(CarLeaseServiceConstants.FIND_CAR_SUCCESS));
  }

  @Test
  public void findUnavailableCarTest() throws Exception {
      when(carDetailsRepository.findByCarNumberIgnoreCase(Mockito.any(String.class))).thenReturn(Optional.empty());
      
      assertThrows(DetailsNotFoundException.class, () -> {
        carDetailsService.findCarByCarNumber("2-XXX-9000");
      });
  }

}
