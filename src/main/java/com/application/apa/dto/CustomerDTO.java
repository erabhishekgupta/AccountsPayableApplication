package com.application.apa.dto;
import java.util.List;

public class CustomerDTO {
	
	private Long customerId;
	
	private String name;
	
	private String email;
	
	private String contactNumber;
	
	private String address;
	
	private List<ContractDTO> contract;
	
	private String gstNumber;
	private String panNumber;

	

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ContractDTO> getContract() {
		return contract;
	}

	public void setContract(List<ContractDTO> contract) {
		this.contract = contract;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}


	
   
	
}
