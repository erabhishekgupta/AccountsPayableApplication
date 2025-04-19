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
public class VendorContract extends Contract {

    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    public VendorContract() {
    	super();
    }

    public VendorContract(LocalDate startDate,
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
                          Vendor vendor, Customer customer,
                          ReferenceSource referenceSource,
                          ReferenceType referenceType) {
        super(startDate, endDate, poReference, recurringBillAmount,
              billingStartDate, billingFrequency, paymentMethod,
              status, paymentStatus, totalAmountWithGst,
              contractDocumentPath, poDocumentPath,referenceSource, referenceType);
        
        this.vendor = vendor;
        this.customer = customer;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    

	

	
    
    
}
