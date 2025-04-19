package com.application.apa.dto;



import java.time.LocalDate;

import com.application.apa.Enum.BillingFrequency;
import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.PaymentMethod;
import com.application.apa.Enum.Payment_Status;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;

public class VendorContractDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private String poReference;
    private Double recurringBillAmount;
    private LocalDate billingStartDate;
    private BillingFrequency billingFrequency;
    private PaymentMethod paymentMethod;
    private ContractStatus status;
    private Payment_Status paymentStatus;
    private double totalAmountWithGst;
    private String contractDocumentPath;
    private String poDocumentPath;
    private ReferenceSource referenceSource;
    private ReferenceType referenceType;
    private Long vendorId;
    private Long customerId;

    // --- Getters and Setters ---

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPoReference() {
        return poReference;
    }

    public void setPoReference(String poReference) {
        this.poReference = poReference;
    }

    public Double getRecurringBillAmount() {
        return recurringBillAmount;
    }

    public void setRecurringBillAmount(Double recurringBillAmount) {
        this.recurringBillAmount = recurringBillAmount;
    }

    public LocalDate getBillingStartDate() {
        return billingStartDate;
    }

    public void setBillingStartDate(LocalDate billingStartDate) {
        this.billingStartDate = billingStartDate;
    }

    public BillingFrequency getBillingFrequency() {
        return billingFrequency;
    }

    public void setBillingFrequency(BillingFrequency billingFrequency) {
        this.billingFrequency = billingFrequency;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status
        		
        		
        		
        		
;
    }

    public Payment_Status getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Payment_Status paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getTotalAmountWithGst() {
        return totalAmountWithGst;
    }

    public void setTotalAmountWithGst(double totalAmountWithGst) {
        this.totalAmountWithGst = totalAmountWithGst;
    }

    public String getContractDocumentPath() {
        return contractDocumentPath;
    }

    public void setContractDocumentPath(String contractDocumentPath) {
        this.contractDocumentPath = contractDocumentPath;
    }

    public String getPoDocumentPath() {
        return poDocumentPath;
    }

    public void setPoDocumentPath(String poDocumentPath) {
        this.poDocumentPath = poDocumentPath;
    }

    public ReferenceSource getReferenceSource() {
        return referenceSource;
    }

    public void setReferenceSource(ReferenceSource referenceSource) {
        this.referenceSource = referenceSource;
    }

    public ReferenceType getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType = referenceType;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
    
    
}

