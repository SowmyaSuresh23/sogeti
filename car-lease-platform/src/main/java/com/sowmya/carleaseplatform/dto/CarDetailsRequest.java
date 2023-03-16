package com.sowmya.carleaseplatform.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sowmya Suresh
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDetailsRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @NotNull(message = "Car number is mandatory.")
  @Size(min = 8, max = 8, message = "Car number should have 8 characters.")
  private String carNumber;

  @NotNull(message = "Car make is mandatory.")
  private String make;

  @NotNull(message = "Car model is mandatory.")
  private String model;

  @NotNull(message = "Car version is mandatory.")
  private String version;

  @NotNull(message = "Number of doors is mandatory.")
  private Integer noOfDoors;

  @NotNull(message = "CO2 emission is mandatory.")
  private Double co2Emission;

  @NotNull(message = "Gross price is mandatory.")
  private Double grossPrice;

  @NotNull(message = "Net price is mandatory.")
  private Double netPrice;

  @NotNull(message = "Mileage is mandatory.")
  private Float mileage;

  @NotNull(message = "Interest rate is mandatory.")
  private Float interestRate;

  @NotNull(message = "User name is mandatory.")
  @Size(min = 5, max = 50, message = "User name can be 5 to 50 characters long.")
  private String userName;
}

