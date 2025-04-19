package com.application.apa.serviceInterface;

import java.util.List;

import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;
import com.application.apa.dto.ContractDTO;
import com.application.apa.dto.VendorContractDTO;
import com.application.apa.model.VendorContract;

public interface VendorContractService {
	
	VendorContract saveVendor(VendorContractDTO dto);

	List<VendorContract> getExpiringContracts(int daysAhead);
	
	VendorContract getVendorContractById(Long id);
	
	List<VendorContract> getAllVendorContracts();
	
	VendorContract updateVendorContract(Long id, VendorContractDTO dto);
	
	void deleteVendorContract(Long id);

	List<VendorContract> getContractsByStatus(ContractStatus status);

	List<VendorContract> getContractsByStatusAndVendorId(ContractStatus status, Long vendorId);

	List<VendorContract> getContractsByReference(ReferenceSource referenceSource, ReferenceType referenceType);

	ContractDTO markAsPaidAndExtendBillDate(Long id);
}
