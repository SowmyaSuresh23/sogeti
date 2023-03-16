package com.sowmya.customerservice.constants;

/**
 * @author Sowmya Suresh
 *
 */
public class CustomerServiceConstants {

  // Response codes
  public static final String SUCCESS_CODE = "200";
  public static final String BAD_REQUEST = "400";
  public static final String INTERNAL_SERVER_ERROR = "500";
  public static final String CONFLICT = "409";
  public static final String NOT_FOUND = "404";

  // Success messages

  public static final String SAVE_NEW_CUSTOMER_SUCCESS = "Customer details saved successfully.";
  public static final String DELETE_CUSTOMER_SUCCESS = "Customer details deleted successfully.";
  public static final String UPDATE_CUSTOMER_SUCCESS = "Customer details updated successfully.";
  public static final String FETCH_CUSTOMER_SUCCESS = "Customer details fetched successfully.";

  // Error messages

  public static final String DUPLICATE_CUSTOMER_NAME = "This email address is already available.";
  public static final String CUSTOMER_NOT_FOUND =
      "Cannot find customer details. Please try with a valid email addredd.";
  public static final String SYSTEM_ERROR =
      "Something went wrong! Please contact system admin or try again later.";
}
