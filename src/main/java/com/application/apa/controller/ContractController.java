//package com.application.apa.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.application.apa.Enum.ContractStatus;
//import com.application.apa.Enum.ReferenceSource;
//import com.application.apa.Enum.ReferenceType;
//import com.application.apa.dto.ContractDTO;
//import com.application.apa.model.Contract;
//import com.application.apa.service.ContractService;
// 
//@RestController
//@RequestMapping("/contracts")
//public class ContractController {
//
//	@package com.application.apa.controller;
//
//import java.util.List;
// 
//@RestController
//@RequestMapping("/contracts")
//public class ContractController {
//
//	@Autowired
//	private ContractService contractService;
//	
////	//api endpoint to save contract details 
////	@PostMapping("/create")
////	public ResponseEntity<Contract> createContract(@RequestBody ContractDTO dto) {
////	    Contract savedContract = contractService.saveContract(dto);
////	    return ResponseEntity.ok(savedContract);
////	}
//	
////	//api endpoint to get contract details
////	@GetMapping("/getAllContracts")
////	public List<ContractDTO> getContracts() {
////		return contractService.getAllContracts();
////	}
//	
//	//api to get the contracts within the expiring date
//	@GetMapping("/expiring")
//	public ResponseEntity<List<ContractDTO>> getExpiringContracts(
//	        @RequestParam(defaultValue = "30") int daysAhead) {
//	    List<ContractDTO> expiringContracts = contractService.getExpiringContracts(daysAhead);
//	    return ResponseEntity.ok(expiringContracts);
//	}
//
//	
//	//api endpoint to genrate new start-date and end-date 
//	@PutMapping("/{id}/extendNewDate")
//	public ResponseEntity<String> markContractAsDone(@PathVariable Long id) {
//		try {
//			contractService.markAsPaidAndExtendedBill(id);
//			return ResponseEntity.ok("Contract Payment has done Now new DueDate is scheduled");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
//		}
//	}
//	
//	//api endpoint to mark payment status as done 
//	@PutMapping("/{id}/markPaymentAsDone")
//	public ResponseEntity<String> markAsPaid(@PathVariable Long id) {
//		try {
//			contractService.markAsPaid(id);
//			return ResponseEntity.ok("Payment status changed to Paid");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
//		}
//	}
//	
//	//api endpoint to delete contract details
//	@DeleteMapping("/{id}/delete")
//	public ResponseEntity<String> deleteContract(@PathVariable Long id) {
//		try {
//			contractService.removeContract(id);
//			return ResponseEntity.ok("Contract Removed Successfully");
//
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error :" + e.getMessage());
//		}
//	}
//	
//	//api endpoint to get contract by status i.e active/expiry
//	@GetMapping("/getContract/status/{status}")
//	public ResponseEntity<List<Contract>> getContractByStatus(@PathVariable ContractStatus status) {
//		List<Contract> contracts = contractService.getActiveContracts(status);
//		return ResponseEntity.ok(contracts);   
//	}
//		
//	//api endpoint to get contract for a particular vendor with 
//	@GetMapping("/getContract/vendorId/{vendorId}/status/{status}")
//	public ResponseEntity<List<Contract>> getContractByStatusAndId(@PathVariable ContractStatus status, @PathVariable Long vendorId ){
//		List<Contract> contracts = contractService.getActiveContractsByStatusAndId(status,vendorId);
//		return ResponseEntity.ok(contracts);
//	}
//	
//	@GetMapping("/getContract/{referenceSource}/{referenceType}")
//	public ResponseEntity<List<Contract>> getContractByRefernceSourceAndRefernceType(@PathVariable ReferenceSource referenceSource, @PathVariable ReferenceType referenceType){
//		List<Contract> contracts = contractService.getContractByReferenceSourceAndType(referenceSource,referenceType);
//		return ResponseEntity.ok(contracts);
//	}
//
//}
//Autowired
//	private ContractService contractService;
//	
////	//api endpoint to save contract details 
////	@PostMapping("/create")
////	public ResponseEntity<Contract> createContract(@RequestBody ContractDTO dto) {
////	    Contract savedContract = contractService.saveContract(dto);
////	    return ResponseEntity.ok(savedContract);
////	}
//	
////	//api endpoint to get contract details
////	@GetMapping("/getAllContracts")
////	public List<ContractDTO> getContracts() {
////		return contractService.getAllContracts();
////	}
//	
//	//api to get the contracts within the expiring date
//	@GetMapping("/expiring")
//	public ResponseEntity<List<ContractDTO>> getExpiringContracts(
//	        @RequestParam(defaultValue = "30") int daysAhead) {
//	    List<ContractDTO> expiringContracts = contractService.getExpiringContracts(daysAhead);
//	    return ResponseEntity.ok(expiringContracts);
//	}
//
//	
//	//api endpoint to genrate new start-date and end-date 
//	@PutMapping("/{id}/extendNewDate")
//	public ResponseEntity<String> markContractAsDone(@PathVariable Long id) {
//		try {
//			contractService.markAsPaidAndExtendedBill(id);
//			return ResponseEntity.ok("Contract Payment has done Now new DueDate is scheduled");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
//		}
//	}
//	
//	//api endpoint to mark payment status as done 
//	@PutMapping("/{id}/markPaymentAsDone")
//	public ResponseEntity<String> markAsPaid(@PathVariable Long id) {
//		try {
//			contractService.markAsPaid(id);
//			return ResponseEntity.ok("Payment status changed to Paid");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
//		}
//	}
//	
//	//api endpoint to delete contract details
//	@DeleteMapping("/{id}/delete")
//	public ResponseEntity<String> deleteContract(@PathVariable Long id) {
//		try {
//			contractService.removeContract(id);
//			return ResponseEntity.ok("Contract Removed Successfully");
//
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error :" + e.getMessage());
//		}
//	}
//	
//	//api endpoint to get contract by status i.e active/expiry
//	@GetMapping("/getContract/status/{status}")
//	public ResponseEntity<List<Contract>> getContractByStatus(@PathVariable ContractStatus status) {
//		List<Contract> contracts = contractService.getActiveContracts(status);
//		return ResponseEntity.ok(contracts);   
//	}
//		
//	//api endpoint to get contract for a particular vendor with 
//	@GetMapping("/getContract/vendorId/{vendorId}/status/{status}")
//	public ResponseEntity<List<Contract>> getContractByStatusAndId(@PathVariable ContractStatus status, @PathVariable Long vendorId ){
//		List<Contract> contracts = contractService.getActiveContractsByStatusAndId(status,vendorId);
//		return ResponseEntity.ok(contracts);
//	}
//	
//	@GetMapping("/getContract/{referenceSource}/{referenceType}")
//	public ResponseEntity<List<Contract>> getContractByRefernceSourceAndRefernceType(@PathVariable ReferenceSource referenceSource, @PathVariable ReferenceType referenceType){
//		List<Contract> contracts = contractService.getContractByReferenceSourceAndType(referenceSource,referenceType);
//		return ResponseEntity.ok(contracts);
//	}
//
//}
