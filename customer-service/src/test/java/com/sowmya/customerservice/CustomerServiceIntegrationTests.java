package com.sowmya.customerservice;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;
import com.sowmya.customerservice.constants.CustomerServiceConstants;
import com.sowmya.customerservice.dto.CustomerDetailsRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceIntegrationTests {

  @Autowired
  private MockMvc mvc;

  private Gson gson = new Gson();

  @Test
  @Order(1)
  public void saveNewCustomerTest() throws Exception {
    CustomerDetailsRequest newCustomer = CustomerDetailsRequest.builder().customerName("Jacob")
        .street("wierdensestraat").houseNumber("11").zipCode("8982 HH").place("Utrecth")
        .email("jacob@fmail.com").phoneNumber("+31698756965").userName("Sowmya").build();

    mvc.perform(post("/customerDetails/saveCustomer").contentType(MediaType.APPLICATION_JSON)
        .content(gson.toJson(newCustomer))).andExpect(status().isOk())
        .andExpect(jsonPath("responseCode", is(CustomerServiceConstants.SUCCESS_CODE)));
  }

  @Test
  @Order(2)
  public void updateCustomerDetailsTest() throws Exception {
    CustomerDetailsRequest newCustomer = CustomerDetailsRequest.builder().customerName("Jacob H")
        .street("wierdensestraat").houseNumber("44").zipCode("8982 HH").place("Utrecth")
        .email("jacob@fmail.com").phoneNumber("+31698756965").userName("Sowmya").build();

    mvc.perform(patch("/customerDetails/updateCustomerDetails")
        .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(newCustomer)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("responseCode", is(CustomerServiceConstants.SUCCESS_CODE)));
  }

  @Test
  @Order(3)
  public void getCustomerDetailsTest() throws Exception {
    mvc.perform(get("/customerDetails/findCustomerDetails").contentType(MediaType.APPLICATION_JSON)
        .param("email", "jacob@fmail.com")).andExpect(status().isOk())
        .andExpect(jsonPath("responseCode", is(CustomerServiceConstants.SUCCESS_CODE)));
  }

  @Test
  @Order(4)
  public void deleteCustomerTest() throws Exception {
    mvc.perform(delete("/customerDetails/deleteCustomer").contentType(MediaType.APPLICATION_JSON)
        .param("email", "jacob@fmail.com")).andExpect(status().isOk())
        .andExpect(jsonPath("responseCode", is(CustomerServiceConstants.SUCCESS_CODE)));
  }
}
