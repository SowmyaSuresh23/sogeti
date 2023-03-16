package com.sowmya.customerservice.service;

import com.sowmya.customerservice.dto.CustomerDetailsRequest;
import com.sowmya.customerservice.dto.CustomerDetailsResponse;
import com.sowmya.customerservice.dto.ResponseDto;

public interface CustomerDetailsService {

  public ResponseDto saveNewCustomer(CustomerDetailsRequest newCustomer);

  public ResponseDto deleteCustomer(String emailAddress);

  public CustomerDetailsResponse findCustomerByEmailAddress(String emailAddress);

  public ResponseDto updateCustomerByEmail(CustomerDetailsRequest updateRequest);
}
