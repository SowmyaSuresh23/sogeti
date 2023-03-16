package com.sowmya.carleaseplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Sowmya Suresh
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerDetailsResponse extends ResponseDto {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String customerName;

  private String street;

  private String houseNumber;

  private String zipCode;

  private String place;

  private String email;

  private String phoneNumber;

}
