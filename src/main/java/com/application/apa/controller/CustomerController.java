package com.application.apa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.application.apa.dto.CustomerContractDTO;
import com.application.apa.dto.CustomerDTO;
import com.application.apa.serviceInterface.CustomerService;
import java.util.List;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
		
	@PostMapping("/create")
	public CustomerDTO createCustomer(@RequestBody CustomerDTO dto) {
		return customerService.createCustomer(dto);
	}
	
	@GetMapping("/{id}")
	public CustomerDTO getCustomer(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}
	
	@GetMapping("/getAllCustomers")
	public List<CustomerDTO> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@PutMapping("/{id}/update")
	public CustomerDTO updateCustomer(@PathVariable Long id , @RequestBody CustomerDTO dto) {
		return customerService.updateCustomer(id, dto);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		 customerService.deleteCustomer(id);
	}
	
//	//mapping to get the contracts of the customer
//	@GetMapping("/{id}/contracts")
//	public ResponseEntity<List<CustomerContractDTO>> getContractsByCustomer(@PathVariable Long id) {
//	    List<CustomerContractDTO> contracts = customerService.findByCustomerId(id);
//	    return ResponseEntity.ok(contracts);
//	}
	
	
	
}
