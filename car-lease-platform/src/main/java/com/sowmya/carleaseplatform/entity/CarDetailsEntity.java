package com.sowmya.carleaseplatform.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sowmya Suresh
 *
 */
@Entity
@Table(name = "car_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDetailsEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -5263566141434880817L;

  @Id
  @Column(name = "car_number", length = 8, nullable = false, unique = true)
  private String carNumber;

  @Column(name = "make", nullable = false)
  private String make;

  @Column(name = "model", nullable = false)
  private String model;

  @Column(name = "version", nullable = false)
  private String version;

  @Column(name = "number_of_doors", nullable = false)
  private Integer noOfDoors;

  @Column(name = "co2_emission", nullable = false)
  private Double co2Emission;

  @Column(name = "gross_price", nullable = false)
  private Double grossPrice;

  @Column(name = "net_price", nullable = false)
  private Double netPrice;

  @Column(name = "mileage", nullable = false)
  private Float mileage;

  @Column(name = "created_user", nullable = false, length = 50)
  private String createdUser;

  @Column(name = "created_timestamp", nullable = false)
  private Timestamp createdTimestamp;

  @Column(name = "updated_user", length = 50)
  private String updatedUser;

  @Column(name = "updated_timestamp")
  private Timestamp updatedTimestamp;

  @OneToOne(mappedBy = "carDetails")
  private CarLeaseDetailsEntity carLeaseDetails;
}
