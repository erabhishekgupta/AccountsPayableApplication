package com.application.apa.model;

import java.time.LocalDate;

import com.application.apa.Enum.BillingFrequency;
import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.PaymentMethod;
import com.application.apa.Enum.Payment_Status;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class CustomerContract extends Contract{
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
	
	
	public CustomerContract() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerContract(LocalDate startDate,
			LocalDate endDate,
			String poReference,
			Double recurringBillAmount,
			LocalDate billingStartDate,
			BillingFrequency billingFrequency, 
			PaymentMethod paymentMethod,
			ContractStatus status,
			Payment_Status paymentStatus,
			double totalAmountWithGst,
			String contractDocumentPath,
			String poDocumentPath, 
			ReferenceSource referenceSource, 
			ReferenceType referenceType,
			Customer customer, Vendor vendor) {
		super(startDate, endDate, poReference, recurringBillAmount, billingStartDate, billingFrequency,
				paymentMethod, status,paymentStatus, totalAmountWithGst, contractDocumentPath, 
				poDocumentPath, referenceSource, referenceType);
		this.customer = customer;
		this.vendor = vendor;
		// TODO Auto-generated constructor stub
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	
	
	
}
