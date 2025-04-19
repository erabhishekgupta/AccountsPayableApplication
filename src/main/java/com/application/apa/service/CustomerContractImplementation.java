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
import com.application.apa.dto.CustomerContractDTO;
import com.application.apa.model.Customer;
import com.application.apa.model.CustomerContract;
import com.application.apa.repository.CustomerContractRepository;
import com.application.apa.repository.CustomerRepository;
import com.application.apa.serviceInterface.CustomerContractService;

@Service
public class CustomerContractImplementation implements CustomerContractService {

	private final CustomerRepository customerRepository;
	private final CustomerContractRepository customerContractRepository;

	// Constructor-based injection for required dependencies
	@Autowired
	public CustomerContractImplementation(CustomerRepository customerRepository,
			CustomerContractRepository customerContractRepository) {
		this.customerRepository = customerRepository;
		this.customerContractRepository = customerContractRepository;
	}

	@Override
	public CustomerContract createCustomerContract(CustomerContractDTO dto) {
		Customer customer = customerRepository.findById(dto.getCustomerId())
				.orElseThrow(() -> new IllegalArgumentException("CUSTOMER ID NOT FOUND"));

		CustomerContract contract = new CustomerContract();
		contract.setCustomer(customer);
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

		return customerContractRepository.save(contract);
	}

	@Override
	public List<CustomerContract> getExpiringContracts(int daysAhead) {
		LocalDate today = LocalDate.now();
		LocalDate thresholdDate = today.plusDays(daysAhead);
		return customerContractRepository.findByEndDateBetween(today, thresholdDate);
	}

	@Override
	public List<CustomerContract> getAllCustomerContracts() {
		return customerContractRepository.findAll();
	}

	@Override
	public CustomerContract getCustomerContractById(Long id) {
		return customerContractRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer Contract not found"));
	}

	@Override
	public CustomerContract updateCustomerContract(Long id, CustomerContractDTO dto) {
		CustomerContract contract = getCustomerContractById(id);

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

		return customerContractRepository.save(contract);
	}

	@Override
	public void deleteCustomerContract(Long id) {
		customerContractRepository.deleteById(id);
	}

	@Override
	public List<CustomerContract> getContractsByStatus(ContractStatus status) {
		return customerContractRepository.findByStatus(status);
	}

	@Override
	public List<CustomerContract> getContractsByStatusAndCustomerId(ContractStatus status, Long customerId) {
		return customerContractRepository.findByCustomerIdAndStatus(customerId, status);
	}

	@Override
	public List<CustomerContract> getContractsByReference(ReferenceSource referenceSource,
			ReferenceType referenceType) {
		return customerContractRepository.findByReferenceSourceAndReferenceType(referenceSource, referenceType);
	}

	 @Override
	    public ContractDTO markAsPaidAndExtendBillDate(Long id) {
	        // Fetch the contract from DB
	        CustomerContract contract = customerContractRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Customer Contract Not Found"));

	        // Prevent re-marking as paid
	        if (contract.getPaymentStatus() == Payment_Status.PAID) {
	            throw new IllegalStateException("Contract already marked as PAID.");
	        }

	        // Mark as PAID
	        contract.setPaymentStatus(Payment_Status.PAID);

	        // Update start date and extend end date
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

	        // Save the updated contract
	        CustomerContract saved = customerContractRepository.save(contract);

	        // Return updated details as DTO
	        ContractDTO dto = new ContractDTO();
	        dto.setStartDate(saved.getStartDate());
	        dto.setEndDate(saved.getEndDate());
	        dto.setPaymentStatus(saved.getPaymentStatus());

	        return dto;
	    }
}
