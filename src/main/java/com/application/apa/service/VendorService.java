package com.application.apa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.apa.Utility.AuditLogger;
import com.application.apa.model.Contract;
import com.application.apa.model.Customer;
import com.application.apa.model.Vendor;
import com.application.apa.model.VendorContract;
import com.application.apa.repository.VendorContractRepository;
import com.application.apa.repository.VendorRepository;
import jakarta.transaction.Transactional;
import com.application.apa.Utility.UpdateUtils;
import com.application.apa.dto.CustomerDTO;
import com.application.apa.dto.GstVerificationResponse;
import com.application.apa.dto.VendorDTO;

@Service
public class VendorService {

	@Autowired
	private VendorRepository vendorRepo;

	@Autowired
	private AuditLogger auditLogger;

	@Autowired
	private UpdateUtils updateUtils;
	
	@Autowired
	private  GstVerificationService gstService;
	
	
	@Autowired
	private VendorContractRepository vendorContractRepository;

	

    public Vendor saveVendor(VendorDTO vendorDTO) {
        
        GstVerificationResponse gstResponse = gstService.verifyGst(vendorDTO.getGstNumber());

        if (!gstResponse.isValid()) {
            throw new IllegalArgumentException("Invalid GST Number");
        }
        String pan = gstResponse.getPan();
       
        Vendor vendor = new Vendor();
        vendor.setName(vendorDTO.getName());
        vendor.setGstNumber(vendorDTO.getGstNumber());
        vendor.setAddress(vendorDTO.getAddress());
        vendor.setPanNumber(pan); 
        vendor.setEmail(vendorDTO.getEmail());
        vendor.setContactInfo(vendorDTO.getContactInfo());
        return vendorRepo.save(vendor);
    }
    
    
	public List<Vendor> getAllVendors() {
		return vendorRepo.findAll();
	}

	public void removeVendor(Long vendorId) {
		 // Check if any vendor contracts are linked to this vendor
	    List<VendorContract> contracts = vendorContractRepository.findByVendorId(vendorId);
	    
	    if (!contracts.isEmpty()) {
	        throw new RuntimeException("Cannot delete vendor with active contracts.");
	    }

	    vendorContractRepository.deleteById(vendorId);
	}

	@Transactional
	public VendorDTO updateVendorDetails(Long id, VendorDTO dto) {
//		return vendorRepo.findById(id).map(existingVendor -> {
//
//			StringBuilder oldValue = new StringBuilder();
//			StringBuilder newValue = new StringBuilder();
//
//			updateUtils.compareAndUpdate(existingVendor.getName(), updatedData.getName(), "name ",
//					existingVendor::setName, oldValue, newValue);
//
//			updateUtils.compareAndUpdate(existingVendor.getName(), updatedData.getName(), "address ",
//					existingVendor::setAddress, oldValue, newValue);
//
//			updateUtils.compareAndUpdate(existingVendor.getName(), updatedData.getName(), "email ",
//					existingVendor::setEmail, oldValue, newValue);
//
//			updateUtils.compareAndUpdate(existingVendor.getName(), updatedData.getName(), "contactInfo ",
//					existingVendor::setContactInfo, oldValue, newValue);
//
//			Vendor savedVendor = vendorRepo.save(existingVendor);
//			if (oldValue.length() > 0 || newValue.length() > 0) {
//				auditLogger.logChange("Vendor", id.toString(), "UPDATE", oldValue.toString(), newValue.toString(),
//						"System");
//			}
//			return savedVendor;
//		});

        Vendor existing = vendorRepo.findById(id)
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

        if (dto.getContactInfo() != null && !dto.getContactInfo().isEmpty()) {
            existing.setContactInfo(dto.getContactInfo());
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
       
        Vendor updatedCustomer = vendorRepo.save(existing);

        
        return mapToDTO(updatedCustomer);
	}
	
	private VendorDTO mapToDTO(Vendor vendor) {

		VendorDTO dto = new VendorDTO();
		dto.setVendorId(vendor.getId());
		dto.setAddress(vendor.getAddress());
		dto.setEmail(vendor.getEmail());
		dto.setContactInfo(vendor.getContactInfo());
		dto.setName(vendor.getName());
		dto.setGstNumber(vendor.getGstNumber());
		dto.setPanNumber(vendor.getPanNumber());

		return dto;
	}


//	public Optional<Object> getCustomerByVendor(Long id) {
//		
//		return null;
//	}

}


