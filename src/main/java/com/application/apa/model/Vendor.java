package com.application.apa.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Invalid Name: Empty name")
	@NotNull(message = "Invalid Name: Name is NULL")
	@Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
	private String name;

	@NotBlank
	private String address;

	@NotBlank(message = "Invalid Phone number: Empty number")
	@NotNull(message = "Invalid Phone number: Number is NULL")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
	private String contactInfo;

	@Email(message = "Invalid email")
	private String email;

	private String gstNumber;
	private String panNumber;

	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
	private List<VendorContract> vendorContracts;

	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
	private List<CustomerContract> customerContracts;
	
	

	public List<VendorContract> getVendorContracts() {
		return vendorContracts;
	}

	public void setVendorContracts(List<VendorContract> vendorContracts) {
		this.vendorContracts = vendorContracts;
	}

	public List<CustomerContract> getCustomerContracts() {
		return customerContracts;
	}

	public void setCustomerContracts(List<CustomerContract> customerContracts) {
		this.customerContracts = customerContracts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Vendor(Long id, String name, String address, String contactInfo, String gstNumber, String panNumber,
			String email, List<CustomerContract> customerContracts, List<VendorContract> vendorContracts) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contactInfo = contactInfo;
		this.gstNumber = gstNumber;
		this.panNumber = panNumber;
		this.email = email;
		this.customerContracts = customerContracts;
		this.vendorContracts = vendorContracts;
	}

	public Vendor() {
		super();

	}

}
