package com.sowmya.carleaseplatform.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sowmya.carleaseplatform.dto.CustomerDetailsResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * @author Sowmya Suresh
 *
 */
@FeignClient(name = "CUSTOMER-SERVICE")
public interface FeignConsumer {

  @GetMapping("/customerDetails/findCustomerDetails")
  public CustomerDetailsResponse findCustomerByEmailAddress(
      @Valid @RequestParam("email") @NotNull(message = "Email is mandatory") @Email(
          message = "Please enter a valid email address") String emailAddress);
}
