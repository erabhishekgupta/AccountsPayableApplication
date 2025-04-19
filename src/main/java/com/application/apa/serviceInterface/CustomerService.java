package com.application.apa.serviceInterface;

import com.application.apa.dto.ContractDTO;
import com.application.apa.dto.CustomerContractDTO;
import com.application.apa.dto.CustomerDTO;
import com.application.apa.model.Customer;

import java.util.List;




public interface CustomerService {
	
	CustomerDTO createCustomer(CustomerDTO dto);
	CustomerDTO getCustomerById(Long id);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(Long id, CustomerDTO dto);
    void deleteCustomer(Long id);
	List<CustomerContractDTO> findByCustomerId(Long id);	
	List<CustomerDTO> getCustomerByVendorId(Long vendorId);
    
}
