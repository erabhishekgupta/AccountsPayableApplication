package com.application.apa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.apa.dto.CustomerDTO;
import com.application.apa.dto.VendorDTO;
import com.application.apa.model.Contract;
import com.application.apa.model.Customer;
import com.application.apa.model.Vendor;
import com.application.apa.service.VendorService;
import com.application.apa.serviceInterface.CustomerService;
import com.application.apa.serviceInterface.CustomerView;



@RequestMapping("/vendors")
@RestController
public class VendorController {

	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private CustomerService customerService;
	
	//api endpoint to create Vendor
	@PostMapping("/create")
	public ResponseEntity<?> createVendor(@RequestBody VendorDTO vendorDTO) {
	    try {
	        Vendor savedVendor = vendorService.saveVendor(vendorDTO);
	        return ResponseEntity.ok(savedVendor);
	    } catch (IllegalArgumentException ex) {
	        return ResponseEntity.badRequest().body(ex.getMessage());
	    }
	}
	
	//api endpoint to get All vendor details
	@GetMapping("/getVendors")
	public List<Vendor> getVendors() {
		return vendorService.getAllVendors();
	}
	
	
	
	//api enpoint to delete vendor details 
	@DeleteMapping("{id}/deleteVendor")
	public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
		try {
			vendorService.removeVendor(id);
			return ResponseEntity.ok("Contract Removed Successfully");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error :" + e.getMessage());
		}
	}
	
	//api endpoint to update vendor details
	@PutMapping("/{id}/updateDetails")
	public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO updatedData) {
		return vendorService.updateVendorDetails(id, updatedData);
	}
	
//	//api to get the list based on customers based on the vendorID ;	
//	@GetMapping("/getCustomers/vendorID/{vendorId}")
//	public ResponseEntity<List<CustomerDTO>> getCustomersByVendor(@PathVariable Long vendorId) {
//	    List<CustomerDTO> customers = customerService.getCustomerByVendorId(vendorId);
//	    return ResponseEntity.ok(customers);
//	}

}



//http://localhost:8080/gst/verify?gstNumber=328652153432634
