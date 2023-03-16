package com.sowmya.carleaseplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sowmya Suresh
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarLeaseRequest {

  @NotNull(message = "Car number is mandatory.")
  @Size(min = 8, max = 8, message = "Car number should have 8 characters.")
  private String carNumber;

  @NotNull(message = "Customer email is mandatory.")
  @Email
  private String customerEmail;

  @NotNull(message = "Duration is mandatory.")
  private Float duration;

  @NotNull(message = "Lease rate is manatory.")
  private Double leaseRate;

  @NotNull(message = "Interest rate is mandatory.")
  private Float interestRate;

  @NotNull(message = "User name is mandatory.")
  @Size(min = 5, max = 50, message = "User name can be 5 to 50 characters long.")
  private String userId;

}
