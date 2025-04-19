package com.application.apa.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.apa.Enum.Payment_Status;
import com.application.apa.model.CustomerContract;
import com.application.apa.model.VendorContract;
import com.application.apa.repository.CustomerContractRepository;
import com.application.apa.repository.CustomerRepository;
import com.application.apa.repository.VendorContractRepository;
import com.application.apa.repository.VendorRepository;
import jakarta.transaction.Transactional;


@Service
public class ContractService {



	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CustomerContractRepository customerContractRepository;
	
	@Autowired
	private VendorContractRepository vendorContractRepository;
    
	
	@Transactional
	public void sendRenewalReminders() {
	    LocalDate today = LocalDate.now();

	    // Process Customer Contracts
	    List<CustomerContract> customerContracts = customerContractRepository.findAll();
	    for (CustomerContract contract : customerContracts) {
	        LocalDate endDate = contract.getEndDate();
	        long daysUntilEnd = ChronoUnit.DAYS.between(today, endDate);

	        if (daysUntilEnd >= 0 && daysUntilEnd <= 5) {
	            if (contract.getPaymentStatus() == Payment_Status.PENDING || contract.getPaymentStatus() == Payment_Status.FAILED) {
	                if (contract.getCustomer() != null && contract.getCustomer().getEmail() != null) {
	                    String customerEmail = contract.getCustomer().getEmail();
	                    System.out.println("Sending Payable Reminder to customer: " + customerEmail);
	                    emailService.sendCustomerRemainder(customerEmail, "Payment Due: Contract Expiring Soon", contract);
	                } else {
	                    System.out.println("Customer email is null for contract ID: " + contract.getId());
	                }
	            } else {
	                System.out.println("Payment status is " + contract.getPaymentStatus() + ". Skipping customer email.");
	            }
	        } else {
	            System.out.println("Customer contract not within 5-day window. Skipping.");
	        }
	    }

	    // Process Vendor Contracts
	    List<VendorContract> vendorContracts = vendorContractRepository.findAll();
	    for (VendorContract contract : vendorContracts) {
	        LocalDate endDate = contract.getEndDate();
	        long daysUntilEnd = ChronoUnit.DAYS.between(today, endDate);

	        if (daysUntilEnd >= 0 && daysUntilEnd <= 5) {
	            if (contract.getPaymentStatus() == Payment_Status.PENDING || contract.getPaymentStatus() == Payment_Status.FAILED) {
	                if (contract.getVendor() != null && contract.getVendor().getEmail() != null) {
	                    String vendorEmail = contract.getVendor().getEmail();
	                    System.out.println("Sending Receivable Reminder to vendor: " + vendorEmail);
	                    emailService.sendVendorRemainder(vendorEmail, "Receivable Alert: Contract Expiring Soon", contract);
	                } else {
	                    System.out.println("Vendor email is null for contract ID: " + contract.getId());
	                }
	            } else {
	                System.out.println("Payment status is " + contract.getPaymentStatus() + ". Skipping vendor email.");
	            }
	        } else {
	            System.out.println("Vendor contract not within 5-day window. Skipping.");
	        }
	    }
	}
	
}



