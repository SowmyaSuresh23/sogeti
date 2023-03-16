package com.sowmya.customerservice.service;

import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sowmya.customerservice.constants.CustomerServiceConstants;
import com.sowmya.customerservice.dto.CustomerDetailsRequest;
import com.sowmya.customerservice.dto.CustomerDetailsResponse;
import com.sowmya.customerservice.dto.ResponseDto;
import com.sowmya.customerservice.entity.CustomerEntity;
import com.sowmya.customerservice.exception.CustomerAlreadyExistsException;
import com.sowmya.customerservice.exception.CustomerNotFoundException;
import com.sowmya.customerservice.repository.CustomerDetailsRepository;
import jakarta.transaction.Transactional;

/**
 * @author Sowmya Suresh
 *
 */
@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private CustomerDetailsRepository customerDetailsRepository;

  /**
   * This method checks if a customer is unique based on email id and saves the customer details.
   * 
   * @param newCustomer details
   * @return response code and response message
   */
  @Transactional
  @Override
  public ResponseDto saveNewCustomer(CustomerDetailsRequest newCustomer) {
    ResponseDto response = new ResponseDto();
    logger.info("Entering into CustomerDetailsServiceImpl.saveNewCustomer");

    if (customerDetailsRepository.findByEmail(newCustomer.getEmail()).isEmpty()) {
      CustomerEntity newCustomerEntity = CustomerEntity.builder()
          .customerName(newCustomer.getCustomerName()).street(newCustomer.getStreet())
          .houseNumber(newCustomer.getHouseNumber()).zipCode(newCustomer.getZipCode())
          .place(newCustomer.getPlace()).email(newCustomer.getEmail())
          .phoneNumber(newCustomer.getPhoneNumber()).createdUser(newCustomer.getUserName())
          .createdTimestamp(new Timestamp(System.currentTimeMillis())).build();

      customerDetailsRepository.save(newCustomerEntity);

      logger.debug(CustomerServiceConstants.SAVE_NEW_CUSTOMER_SUCCESS);
      response.setResponseCode(CustomerServiceConstants.SUCCESS_CODE);
      response.setResponseMessage(CustomerServiceConstants.SAVE_NEW_CUSTOMER_SUCCESS);
    } else {

      logger.error(CustomerServiceConstants.DUPLICATE_CUSTOMER_NAME);
      throw new CustomerAlreadyExistsException(CustomerServiceConstants.DUPLICATE_CUSTOMER_NAME);
    }

    logger.info("Exiting CustomerDetailsServiceImpl.saveNewCustomer");
    return response;
  }

  /**
   * Deletes a customer based on email address. It throws an exception if customer is not found
   * 
   * @param emailAddress to be deleted
   * @return response code and response message
   */
  @Transactional
  @Override
  public ResponseDto deleteCustomer(String emailAddress) {
    logger.info("Entering into CustomerDetailsServiceImpl.deleteCustomer");
    ResponseDto response = new ResponseDto();

    Long deleteCount = customerDetailsRepository.deleteByEmail(emailAddress);

    if (deleteCount > 0) {
      logger.debug(CustomerServiceConstants.DELETE_CUSTOMER_SUCCESS);
      response.setResponseMessage(CustomerServiceConstants.DELETE_CUSTOMER_SUCCESS);
      response.setResponseCode(CustomerServiceConstants.SUCCESS_CODE);
    } else {
      logger.error(CustomerServiceConstants.CUSTOMER_NOT_FOUND);
      throw new CustomerNotFoundException(CustomerServiceConstants.CUSTOMER_NOT_FOUND);
    }

    logger.info("Exiting CustomerDetailsServiceImpl.deleteCustomer");
    return response;
  }

  /**
   * This method finds details of a customer by email ID. It returns an exception if customer is not
   * found.
   * 
   * @param emailAddress to be searched
   * @return customer details
   */
  @Transactional
  @Override
  public CustomerDetailsResponse findCustomerByEmailAddress(String emailAddress) {
    logger.info("Entering into CustomerDetailsServiceImpl.findCustomerByEmailAddress");

    CustomerEntity customerEntity = customerDetailsRepository.findByEmail(emailAddress).orElseThrow(
        () -> new CustomerNotFoundException(CustomerServiceConstants.CUSTOMER_NOT_FOUND));

    CustomerDetailsResponse response =
        CustomerDetailsResponse.builder().customerName(customerEntity.getCustomerName())
            .street(customerEntity.getStreet()).houseNumber(customerEntity.getHouseNumber())
            .zipCode(customerEntity.getZipCode()).place(customerEntity.getPlace())
            .email(customerEntity.getEmail()).phoneNumber(customerEntity.getPhoneNumber())
            .responseCode(CustomerServiceConstants.SUCCESS_CODE)
            .responseMessage(CustomerServiceConstants.FETCH_CUSTOMER_SUCCESS).build();

    logger.debug(CustomerServiceConstants.FETCH_CUSTOMER_SUCCESS);

    logger.info("Exiting CustomerDetailsServiceImpl.findCustomerByEmailAddress");
    return response;

  }

  /**
   * This method updates customer details based on email ID. It throws an exception if the customer
   * is not found.
   * 
   * @param updateRequest parameters to update
   * @return response code and response message
   */
  @Transactional
  @Override
  public ResponseDto updateCustomerByEmail(CustomerDetailsRequest updateRequest) {
    logger.info("Entering into CustomerDetailsServiceImpl.updateCustomerByEmail");

    CustomerEntity existingCustomer =
        customerDetailsRepository.findByEmail(updateRequest.getEmail()).orElseThrow(
            () -> new CustomerNotFoundException(CustomerServiceConstants.CUSTOMER_NOT_FOUND));
    existingCustomer.setCustomerName(updateRequest.getCustomerName());
    existingCustomer.setStreet(updateRequest.getStreet());
    existingCustomer.setHouseNumber(updateRequest.getHouseNumber());
    existingCustomer.setZipCode(updateRequest.getZipCode());
    existingCustomer.setPlace(updateRequest.getPlace());
    existingCustomer.setPhoneNumber(updateRequest.getPhoneNumber());
    existingCustomer.setUpdatedUser(updateRequest.getUserName());
    existingCustomer.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));

    customerDetailsRepository.save(existingCustomer);

    logger.debug(CustomerServiceConstants.UPDATE_CUSTOMER_SUCCESS);
    ResponseDto response = new ResponseDto();
    response.setResponseCode(CustomerServiceConstants.SUCCESS_CODE);
    response.setResponseMessage(CustomerServiceConstants.UPDATE_CUSTOMER_SUCCESS);

    logger.info("Exiting CustomerDetailsServiceImpl.updateCustomerByEmail");
    return response;
  }
}
