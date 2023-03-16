package com.sowmya.carleaseplatform.controller;

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
import com.sowmya.carleaseplatform.dto.CarDetailsRequest;
import com.sowmya.carleaseplatform.dto.CarDetailsResponse;
import com.sowmya.carleaseplatform.dto.ResponseDto;
import com.sowmya.carleaseplatform.service.CarDetailsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author Sowmya Suresh
 *
 */
@RestController
@RequestMapping("/carDetails")
@Validated
public class CarDetailsController {

  @Autowired
  CarDetailsService carDetailsService;

  @PostMapping("/saveCarDetails")
  public ResponseDto saveCarDetails(@Valid @RequestBody CarDetailsRequest newCar) {
    return carDetailsService.saveCarDetails(newCar);
  }

  @DeleteMapping("/deleteCar")
  public ResponseDto deleteCar(
      @RequestParam("carNumber") @Valid @NotNull(message = "Car number cannot be null.") @Size(
          min = 8, max = 8, message = "Car number should have 8 characters.") String carNumber) {
    return carDetailsService.deleteCar(carNumber);
  }

  @GetMapping("/getCarDetails")
  public CarDetailsResponse findCarByCarNumber(
      @RequestParam("carNumber") @Valid @NotNull(message = "Car number cannot be null.") @Size(
          min = 8, max = 8, message = "Car number should have 8 characters.") String carNumber) {
    return carDetailsService.findCarByCarNumber(carNumber);
  }

  @PatchMapping("/updateCarDetails")
  public ResponseDto updateCarDetailsByName(@Valid @RequestBody CarDetailsRequest request) {
    return carDetailsService.updateCarDetailsByName(request);
  }
}
