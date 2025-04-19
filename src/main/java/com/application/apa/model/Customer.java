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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Invalid Name: Empty name")
	@NotNull(message = "Invalid Name: Name is NULL")
	@Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
	private String name;

	@Email
	private String email;

	@NotBlank(message = "Invalid Phone number: Empty number")
	@NotNull(message = "Invalid Phone number: Number is NULL")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
	private String contactNumber;


	private String address;

	public Customer() {
	}

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<VendorContract> vendorContracts;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerContract> customerContracts;

	private String gstNumber;
	private String panNumber;
	
	

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

	public Customer(Long id, String name, String email, String contactNumber, String address, 
			String gstNumber, String panNumber,List<VendorContract> vendorContracts,List<CustomerContract> customerContracts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
		this.address = address;
		this.gstNumber = gstNumber;
		this.panNumber = panNumber;
		this.vendorContracts = vendorContracts;
		this.customerContracts = customerContracts;
	}
}
