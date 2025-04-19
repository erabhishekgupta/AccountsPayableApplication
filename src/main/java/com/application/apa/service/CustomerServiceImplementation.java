package com.application.apa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.apa.dto.CustomerContractDTO;
import com.application.apa.dto.CustomerDTO;
import com.application.apa.dto.GstVerificationResponse;
import com.application.apa.model.Customer;
import com.application.apa.repository.CustomerContractRepository;
import com.application.apa.repository.CustomerRepository;
import com.application.apa.serviceInterface.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
    private CustomerContractRepository customerContractRepository;

	@Autowired
	private GstVerificationService gstService;

	@Override
	public CustomerDTO createCustomer(CustomerDTO dto) {

		Customer customer = mapToEntity(dto);
		customer = customerRepo.save(customer);

		return mapToDTO(customer);
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {

		Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer Not Found"));
		return mapToDTO(customer);
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {

		return customerRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
		
        Customer existing = customerRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer Not Found"));

       
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            existing.setName(dto.getName());
        }

        if (dto.getAddress() != null && !dto.getAddress().isEmpty()) {
            existing.setAddress(dto.getAddress());
        }

        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            existing.setEmail(dto.getEmail());
        }

        if (dto.getContactNumber() != null && !dto.getContactNumber().isEmpty()) {
            existing.setContactNumber(dto.getContactNumber());
        }

        if (dto.getGstNumber() != null && !dto.getGstNumber().isEmpty()) {
            existing.setGstNumber(dto.getGstNumber());
        }
        GstVerificationResponse gstResponse = gstService.verifyGst(dto.getGstNumber());
		if (!gstResponse.isValid()) {
			throw new IllegalArgumentException("Invalid GST Number");
		}
		String pan = gstResponse.getPan();
		existing.setPanNumber(pan);
       
        Customer updatedCustomer = customerRepo.save(existing);

        
        return mapToDTO(updatedCustomer);
	}

	@Override
	@Transactional
	public void deleteCustomer(Long id) {
		 // Delete all contracts of this customer first
	    Customer customer = customerRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Customer Not Found"));
	    
	    customerContractRepository.deleteByCustomerId(id);

	    // Then delete the customer
	    customerRepo.delete(customer);

	}

	private Customer mapToEntity(CustomerDTO dto) {

		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());
		customer.setContactNumber(dto.getContactNumber());
		customer.setAddress(dto.getAddress());
		customer.setGstNumber(dto.getGstNumber());
		GstVerificationResponse gstResponse = gstService.verifyGst(dto.getGstNumber());
		if (!gstResponse.isValid()) {
			throw new IllegalArgumentException("Invalid GST Number");
		}
		String pan = gstResponse.getPan();
		customer.setPanNumber(pan);
		return customer;

	}

	private CustomerDTO mapToDTO(Customer customer) {

		CustomerDTO dto = new CustomerDTO();
		dto.setCustomerId(customer.getId());
		dto.setAddress(customer.getAddress());
		dto.setEmail(customer.getEmail());
		dto.setContactNumber(customer.getContactNumber());
		dto.setName(customer.getName());
		dto.setGstNumber(customer.getGstNumber());
		dto.setPanNumber(customer.getPanNumber());

		return dto;
	}

	@Override
	public List<CustomerContractDTO> findByCustomerId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerDTO> getCustomerByVendorId(Long vendorId) {
		// TODO Auto-generated method stub
		return null;
	}



}
