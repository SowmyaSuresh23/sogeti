package com.sowmya.carleaseplatform.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "car_lease_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarLeaseDetailsEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "lease_id")
  private Long leaseId;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "car_ref_id", unique = true)
  private CarDetailsEntity carDetails;

  @Column(name = "duration", nullable = false)
  private Float duration;

  @Column(name = "interest_rate", nullable = false)
  private Float interestRate;

  @Column(name = "lease_rate", nullable = false)
  private Double leaseRate;

  @Column(name = "customer_email", nullable = false)
  private String customerEmail;


  @Column(name = "created_user", nullable = false, length = 50)
  private String createdUser;

  @Column(name = "created_timestamp", nullable = false)
  private Timestamp createdTimestamp;

  @Column(name = "updated_user", length = 50)
  private String updatedUser;

  @Column(name = "updated_timestamp")
  private Timestamp updatedTimestamp;
}
