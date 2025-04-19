package com.application.apa.serviceInterface;

import java.util.List;

import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;
import com.application.apa.dto.ContractDTO;
import com.application.apa.dto.CustomerContractDTO;
import com.application.apa.model.CustomerContract;


public interface CustomerContractService {



	List<CustomerContract> getExpiringContracts(int daysAhead);

	CustomerContract createCustomerContract(CustomerContractDTO dto);

	List<CustomerContract> getAllCustomerContracts();

	CustomerContract getCustomerContractById(Long id);

	CustomerContract updateCustomerContract(Long id, CustomerContractDTO dto);

	void deleteCustomerContract(Long id);
	
	List<CustomerContract> getContractsByStatus(ContractStatus status);
	List<CustomerContract> getContractsByStatusAndCustomerId(ContractStatus status, Long customerId);
	List<CustomerContract> getContractsByReference(ReferenceSource referenceSource, ReferenceType referenceType);

	ContractDTO markAsPaidAndExtendBillDate(Long id);
}
