package com.sowmya.customerservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customer_id")
  private Long customerId;

  @Column(name = "customer_name", nullable = false, length = 50)
  private String customerName;

  @Column(name = "street", nullable = false)
  private String street;

  @Column(name = "house_number", nullable = false)
  private String houseNumber;

  @Column(name = "zip_code", nullable = false, length = 7)
  private String zipCode;

  @Column(name = "place", nullable = false)
  private String place;

  @Column(name = "email_address", nullable = false, unique = true)
  private String email;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "created_user", nullable = false, length = 50)
  private String createdUser;

  @Column(name = "created_timestamp", nullable = false)
  private Timestamp createdTimestamp;

  @Column(name = "uodated_user", length = 50)
  private String updatedUser;

  @Column(name = "updated_timestamp")
  private Timestamp updatedTimestamp;

}
