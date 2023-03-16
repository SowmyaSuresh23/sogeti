package com.sowmya.carleaseplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sowmya.carleaseplatform.entity.CarLeaseDetailsEntity;

/**
 * @author Sowmya Suresh
 *
 */
@Repository
public interface CarLeaseRepository extends JpaRepository<CarLeaseDetailsEntity, Long> {

  public Long deleteByLeaseId(Long leaseId);

}
