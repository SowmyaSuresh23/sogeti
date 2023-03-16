package com.sowmya.carleaseplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sowmya.carleaseplatform.dto.CarLeaseCalculationRequest;
import com.sowmya.carleaseplatform.dto.CarLeaseRequest;
import com.sowmya.carleaseplatform.dto.LeaseDetailsResponse;
import com.sowmya.carleaseplatform.dto.ResponseDto;
import com.sowmya.carleaseplatform.service.CarLeaseDetailsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * @author Sowmya Suresh
 *
 */
@RestController
@Validated
@RequestMapping("/carLease")
public class CarLeaseController {

  @Autowired
  CarLeaseDetailsService carLeaseDetailsService;

  @PostMapping("/createNewLease")
  public Long createNewLease(@Valid @RequestBody CarLeaseRequest request) {
    return carLeaseDetailsService.createNewLease(request);
  }

  @GetMapping("/calculateLease")
  public Double calculateLeaseRate(@Valid @RequestBody CarLeaseCalculationRequest request) {
    return carLeaseDetailsService.calculateLeaseRate(request);
  }

  @GetMapping("/getLeaseDetails")
  public LeaseDetailsResponse findLeaseDetails(
      @RequestParam("leaseId") @Valid @NotNull(message = "Lease ID cannot be null.") Long leaseId) {
    return carLeaseDetailsService.findLeaseDetails(leaseId);
  }

  @DeleteMapping("/deleteLeaseDetails")
  public ResponseDto deleteLeaseDetails(
      @RequestParam("leaseId") @Valid @NotNull(message = "Lease ID cannot be null.") Long leaseId) {
    return carLeaseDetailsService.deleteLeaseDetails(leaseId);
  }
}
