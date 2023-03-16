package com.sowmya.carleaseplatform.service;

import com.sowmya.carleaseplatform.dto.CarLeaseCalculationRequest;
import com.sowmya.carleaseplatform.dto.CarLeaseRequest;
import com.sowmya.carleaseplatform.dto.LeaseDetailsResponse;
import com.sowmya.carleaseplatform.dto.ResponseDto;

public interface CarLeaseDetailsService {

  public Double calculateLeaseRate(CarLeaseCalculationRequest request);

  public Long createNewLease(CarLeaseRequest request);

  public LeaseDetailsResponse findLeaseDetails(Long leaseId);

  public ResponseDto deleteLeaseDetails(Long leaseId);
}
