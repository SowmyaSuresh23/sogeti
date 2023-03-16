package com.sowmya.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sowmya.customerservice.dto.CustomerDetailsRequest;
import com.sowmya.customerservice.dto.CustomerDetailsResponse;
import com.sowmya.customerservice.dto.ResponseDto;
import com.sowmya.customerservice.service.CustomerDetailsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * @author Sowmya Suresh
 *
 */
@RestController
@RequestMapping("/customerDetails")
@Validated
public class CustomerDetailsController {

  @Autowired
  CustomerDetailsService customerDetailsService;

  @PostMapping("/saveCustomer")
  public ResponseDto saveNewCustomer(@Valid @RequestBody CustomerDetailsRequest newCustomer) {
    return customerDetailsService.saveNewCustomer(newCustomer);
  }

  @DeleteMapping("/deleteCustomer")
  public ResponseDto deleteCustomer(
      @Valid @RequestParam("email") @NotNull(message = "Email is mandatory") @Email(
          message = "Please enter a valid email address") String emailAddress) {
    return customerDetailsService.deleteCustomer(emailAddress);
  }

  @GetMapping("/findCustomerDetails")
  public CustomerDetailsResponse findCustomerByEmailAddress(
      @Valid @RequestParam("email") @NotNull(message = "Email is mandatory") @Email(
          message = "Please enter a valid email address") String emailAddress) {
    return customerDetailsService.findCustomerByEmailAddress(emailAddress);
  }

  @PatchMapping("/updateCustomerDetails")
  public ResponseDto updateCustomerByEmail(
      @Valid @RequestBody CustomerDetailsRequest updateRequest) {
    return customerDetailsService.updateCustomerByEmail(updateRequest);
  }
}
