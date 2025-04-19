package com.application.apa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;
import com.application.apa.dto.ContractDTO;
import com.application.apa.dto.VendorContractDTO;
import com.application.apa.model.VendorContract;
import com.application.apa.serviceInterface.VendorContractService;

@RestController
@RequestMapping("/api/vendor-contracts")
public class VendorContractController {

    @Autowired
    private VendorContractService vendorContractService;

    @PostMapping("/create")
    public ResponseEntity<?> createVendorContract(@RequestBody VendorContractDTO dto) {
        try {
            VendorContract contract = vendorContractService.saveVendor(dto);
            return ResponseEntity.ok(contract);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating vendor contract: " + e.getMessage());
        }
    }

    @GetMapping("/expiring")
    public ResponseEntity<?> getExpiringContracts(@RequestParam(defaultValue = "7") int days) {
        try {
            List<VendorContract> expiringContracts = vendorContractService.getExpiringContracts(days);
            return ResponseEntity.ok(expiringContracts);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching expiring contracts: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorContractById(@PathVariable Long id) {
        try {
            VendorContract contract = vendorContractService.getVendorContractById(id);
            return ResponseEntity.ok(contract);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Vendor contract not found: " + e.getMessage());
        }
    }

    @GetMapping("/getAllVendors")
    public ResponseEntity<?> getAllVendorContracts() {
        try {
            List<VendorContract> contracts = vendorContractService.getAllVendorContracts();
            return ResponseEntity.ok(contracts);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching vendor contracts: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/updateVendor")
    public ResponseEntity<?> updateVendorContract(@PathVariable Long id, @RequestBody VendorContractDTO dto) {
        try {
            VendorContract updated = vendorContractService.updateVendorContract(id, dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating vendor contract: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVendorContract(@PathVariable Long id) {
        try {
            vendorContractService.deleteVendorContract(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error deleting vendor contract: " + e.getMessage());
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getVendorContractsByStatus(@PathVariable ContractStatus status) {
        try {
            List<VendorContract> contracts = vendorContractService.getContractsByStatus(status);
            return ResponseEntity.ok(contracts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/vendorId/{vendorId}/status/{status}")
    public ResponseEntity<?> getVendorContractsByStatusAndVendorId(@PathVariable Long vendorId, @PathVariable ContractStatus status) {
        try {
            List<VendorContract> contracts = vendorContractService.getContractsByStatusAndVendorId(status, vendorId);
            return ResponseEntity.ok(contracts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{referenceSource}/{referenceType}")
    public ResponseEntity<?> getVendorContractsByReference(@PathVariable ReferenceSource referenceSource, @PathVariable ReferenceType referenceType) {
        try {
            List<VendorContract> contracts = vendorContractService.getContractsByReference(referenceSource, referenceType);
            return ResponseEntity.ok(contracts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}/mark-paid")
    public ContractDTO markAsPaidAndExtendBill(@PathVariable Long id) {
        return vendorContractService.markAsPaidAndExtendBillDate(id);
    }
}
