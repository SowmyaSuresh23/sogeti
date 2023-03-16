package com.sowmya.carleaseplatform.constants;

/**
 * @author Sowmya Suresh
 *
 */
public class CarLeaseServiceConstants {

  // Response codes
  public static final String SUCCESS_CODE = "200";
  public static final String BAD_REQUEST = "400";
  public static final String INTERNAL_SERVER_ERROR = "500";
  public static final String CONFLICT = "409";
  public static final String NOT_FOUND = "404";

  // Success messages
  public static final String NEW_CAR_SAVE_SUCCESS = "Car details have been saved successfully!";
  public static final String DELETE_CAR_SUCCESS = "Car details have been deleted successfully!";
  public static final String FIND_CAR_SUCCESS = "Car details have been fetched successfully!";
  public static final String UPDATE_CAR_SUCCESS = "Car details have been updated successfully!";
  public static final String NEW_LEASE_SAVE_SUCCESS = "New lease has been created successfully!";
  public static final String LEASE_DELETE_SUCCESS = "Lease has been deleted successfully!";

  // Error messages

  public static final String DUPLICATE_CAR_NAME =
      "This car is already available. Please provide a new car detail or try to update the details.";
  public static final String CAR_NOT_FOUND =
      "Cannot find the car number. Please try with a valid car number.";
  public static final String SYSTEM_ERROR =
      "Something went wrong! Please contact system admin or try again later.";
  public static final String CAR_UNAVAILABLE_FOR_LEASE =
      "This car is currently unavailabe for lease.";
  public static final String LEASE_NOT_FOUND =
      "Cannot find the lease ID. Please try with a valid lease ID.";
  public static final String CAR__LEASE_FOUND =
      "Car is linked with a lease. Cannot delete the car.";
  public static final String CUSTOMER_NOT_FOUND =
      "Cannot find the customer details. Please try with a valid customer ID.";
}
