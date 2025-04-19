package com.application.apa.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.Payment_Status;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;
import com.application.apa.dto.ContractDTO;
import com.application.apa.dto.VendorContractDTO;
import com.application.apa.model.CustomerContract;
import com.application.apa.model.Vendor;
import com.application.apa.model.VendorContract;
import com.application.apa.repository.VendorContractRepository;
import com.application.apa.repository.VendorRepository;
import com.application.apa.serviceInterface.VendorContractService;


@Service
public class VendorContractImplementation implements VendorContractService {

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private VendorContractRepository vendorContractRepository;

	@Override
	public VendorContract saveVendor(VendorContractDTO dto) {
		Vendor vendor = vendorRepository.findById(dto.getVendorId())
				.orElseThrow(() -> new IllegalArgumentException("VENDOR ID NOT FOUND"));

		VendorContract contract = new VendorContract();

		contract.setVendor(vendor);
		contract.setStartDate(dto.getStartDate());
		contract.setEndDate(dto.getEndDate());
		contract.setRecurringBillAmount(dto.getRecurringBillAmount());
		contract.setBillingFrequency(dto.getBillingFrequency());
		contract.setPaymentMethod(dto.getPaymentMethod());
		contract.setPaymentStatus(dto.getPaymentStatus());
		contract.setPoReference(dto.getPoReference());
		contract.setReferenceSource(dto.getReferenceSource());
		contract.setReferenceType(dto.getReferenceType());
		contract.setBillingStartDate(dto.getBillingStartDate());
		contract.setStatus(dto.getStatus());

		return vendorContractRepository.save(contract);
	}

	@Override
    public List<VendorContract> getExpiringContracts(int daysAhead) {
        LocalDate today = LocalDate.now();
        LocalDate thresholdDate = today.plusDays(daysAhead);
        return vendorContractRepository.findByEndDateBetween(today, thresholdDate);
    }

	@Override
    public VendorContract getVendorContractById(Long id) {
        return vendorContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VendorContract not found"));
    }

	@Override
	public List<VendorContract> getAllVendorContracts() {
		 return vendorContractRepository.findAll();
	}
	
	  @Override
	    public VendorContract updateVendorContract(Long id, VendorContractDTO dto) {
	        VendorContract contract = getVendorContractById(id);

	        contract.setStartDate(dto.getStartDate());
	        contract.setEndDate(dto.getEndDate());
	        contract.setPoReference(dto.getPoReference());
	        contract.setRecurringBillAmount(dto.getRecurringBillAmount());
	        contract.setBillingStartDate(dto.getBillingStartDate());
	        contract.setBillingFrequency(dto.getBillingFrequency());
	        contract.setPaymentMethod(dto.getPaymentMethod());
	        contract.setStatus(dto.getStatus());
	        contract.setPaymentStatus(dto.getPaymentStatus());
	        contract.setTotalAmountWithGst(dto.getTotalAmountWithGst());
	        contract.setContractDocumentPath(dto.getContractDocumentPath());
	        contract.setPoDocumentPath(dto.getPoDocumentPath());
	        contract.setReferenceSource(dto.getReferenceSource());
	        contract.setReferenceType(dto.getReferenceType());

	        return vendorContractRepository.save(contract);
	    }

	    @Override
	    public void deleteVendorContract(Long id) {
	        vendorContractRepository.deleteById(id);
	    }

		@Override
		public List<VendorContract> getContractsByStatus(ContractStatus status) {
			return vendorContractRepository.findByStatus(status);
		}

		@Override
		public List<VendorContract> getContractsByStatusAndVendorId(ContractStatus status, Long vendorId) {
			return vendorContractRepository.findByStatusAndVendorId(status, vendorId);
		}

		@Override
		public List<VendorContract> getContractsByReference(ReferenceSource referenceSource,
				ReferenceType referenceType) {
			return vendorContractRepository.findByReferenceSourceAndReferenceType(referenceSource, referenceType);
		}

		 @Override
		    public ContractDTO markAsPaidAndExtendBillDate(Long id) {
		        VendorContract contract = vendorContractRepository.findById(id)
		                .orElseThrow(() -> new RuntimeException("Vendor Contract Not Found"));

		        if (contract.getPaymentStatus() == Payment_Status.PAID) {
		            throw new IllegalStateException("Contract already marked as PAID.");
		        }

		        contract.setPaymentStatus(Payment_Status.PAID);
		        LocalDate oldEndDate = contract.getEndDate();
		        contract.setStartDate(oldEndDate);

		        LocalDate newEndDate = switch (contract.getBillingFrequency()) {
		            case WEEKLY -> oldEndDate.plusWeeks(1);
		            case MONTHLY -> oldEndDate.plusMonths(1);
		            case QUATERLY -> oldEndDate.plusMonths(3);
		            case YEARLY -> oldEndDate.plusYears(1);
				default -> throw new IllegalArgumentException("Unexpected value: " + contract.getBillingFrequency());
		        };

		        contract.setEndDate(newEndDate);
		        
		        VendorContract saved = vendorContractRepository.save(contract);

		        ContractDTO dto = new ContractDTO();
		        dto.setStartDate(saved.getStartDate());
		        dto.setEndDate(saved.getEndDate());
		        dto.setPaymentStatus(saved.getPaymentStatus());

		        return dto;
		    }

}
