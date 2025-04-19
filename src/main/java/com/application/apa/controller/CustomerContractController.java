package com.application.apa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;
import com.application.apa.dto.ContractDTO;
import com.application.apa.dto.CustomerContractDTO;
import com.application.apa.model.CustomerContract;
import com.application.apa.serviceInterface.CustomerContractService;

@RestController
@RequestMapping("/api/customer-contracts")
public class CustomerContractController {

    @Autowired
    private CustomerContractService customerContractService;

    @PostMapping("/create")
    public ResponseEntity<?> createCustomerContract(@RequestBody CustomerContractDTO dto) {
        try {
            CustomerContract savedContract = customerContractService.createCustomerContract(dto);
            return ResponseEntity.ok(savedContract);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating contract: " + e.getMessage());
        }
    }

    @GetMapping("/expiring")
    public ResponseEntity<?> getExpiringContracts(@RequestParam(defaultValue = "5") int days) {
        try {
            List<CustomerContract> expiringContracts = customerContractService.getExpiringContracts(days);
            return ResponseEntity.ok(expiringContracts);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching expiring contracts: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerContractById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(customerContractService.getCustomerContractById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Contract not found: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomerContract(@PathVariable Long id, @RequestBody CustomerContractDTO dto) {
        try {
            return ResponseEntity.ok(customerContractService.updateCustomerContract(id, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating contract: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerContract(@PathVariable Long id) {
        try {
            customerContractService.deleteCustomerContract(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting contract: " + e.getMessage());
        }
    }

    @GetMapping("/getAllCustomersContracts")
    public ResponseEntity<?> getAllCustomerContracts() {
        try {
            return ResponseEntity.ok(customerContractService.getAllCustomerContracts());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching contracts: " + e.getMessage());
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getCustomerContractsByStatus(@PathVariable ContractStatus status) {
        try {
            List<CustomerContract> contracts = customerContractService.getContractsByStatus(status);
            return ResponseEntity.ok(contracts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/customerId/{customerId}/status/{status}")
    public ResponseEntity<?> getCustomerContractsByStatusAndCustomerId(@PathVariable Long customerId, @PathVariable ContractStatus status) {
        try {
            List<CustomerContract> contracts = customerContractService.getContractsByStatusAndCustomerId(status, customerId);
            return ResponseEntity.ok(contracts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{referenceSource}/{referenceType}")
    public ResponseEntity<?> getCustomerContractsByReference(@PathVariable ReferenceSource referenceSource, @PathVariable ReferenceType referenceType) {
        try {
            List<CustomerContract> contracts = customerContractService.getContractsByReference(referenceSource, referenceType);
            return ResponseEntity.ok(contracts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}/mark-paid-extend")
    public ResponseEntity<ContractDTO> markPaidAndExtend(@PathVariable Long id) {
        return ResponseEntity.ok(customerContractService.markAsPaidAndExtendBillDate(id));
    }
}