package com.application.apa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;
import com.application.apa.model.CustomerContract;

@Repository
public interface CustomerContractRepository extends JpaRepository<CustomerContract, Long> {

    List<CustomerContract> findByEndDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<CustomerContract> findById(Long id);

    void deleteById(Long id);

    List<CustomerContract> findByStatus(ContractStatus status);

    List<CustomerContract> findByCustomerIdAndStatus(Long customerId, ContractStatus status);

    List<CustomerContract> findByReferenceSourceAndReferenceType(ReferenceSource referenceSource,
            ReferenceType referenceType);
    
    void deleteByCustomerId(Long customerId);
}
