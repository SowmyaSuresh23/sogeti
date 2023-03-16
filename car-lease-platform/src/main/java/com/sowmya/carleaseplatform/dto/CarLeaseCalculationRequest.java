package com.sowmya.carleaseplatform.dto;

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
public class CarLeaseCalculationRequest {

  @NotNull(message = "Car number is mandatory")
  @Size(min = 8, max = 8, message = "Car number should have 8 characters.")
  private String carNumber;

  @NotNull(message = "Duration is mandatory")
  private Float duration;

  @NotNull(message = "Interest rate is mandatory")
  private Float interestRate;
}
