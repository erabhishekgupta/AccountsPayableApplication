package com.application.apa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;
import com.application.apa.model.VendorContract;


@Repository
public interface VendorContractRepository extends JpaRepository<VendorContract, Long>{

	@SuppressWarnings("unchecked")
	VendorContract save(VendorContract contract);
	
	List<VendorContract> findByEndDateBetween(LocalDate startDate, LocalDate endDate);

	Optional<VendorContract> findById(Long id);

	List<VendorContract> findAll();

	void deleteById(Long id);

	List<VendorContract> findByStatus(ContractStatus status);

	List<VendorContract> findByStatusAndVendorId(ContractStatus status, Long vendorId);

	List<VendorContract> findByReferenceSourceAndReferenceType(ReferenceSource referenceSource,
			ReferenceType referenceType);

	List<VendorContract> findByVendorId(Long vendorId);

}
