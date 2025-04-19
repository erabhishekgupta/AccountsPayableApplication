package com.application.apa.dto;

import java.time.LocalDate;

import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.Payment_Status;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;

public class ContractDTO {
	private Long id;
	private Long vendorId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String poReference;
	private Double recurringBillAmount;
	private String billingFrequency;
	private LocalDate billingStartDate;
	private String paymentMethod;
	private ContractStatus status;
	private Payment_Status paymentStatus;
	private double TotalAmountWithGst;
	private String contractDocumentPath;
	private String poDocumentPath;
	private Long customerId;
	private String  VendorName;
	private ReferenceSource referenceSource;
	private ReferenceType referenceType;
	
	public String getVendorName() {
		return VendorName;
	}
	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
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
	public String getBillingFrequency() {
		return billingFrequency;
	}
	public void setBillingFrequency(String billingFrequency) {
		this.billingFrequency = billingFrequency;
	}
	public LocalDate getBillingStartDate() {
		return billingStartDate;
	}
	public void setBillingStartDate(LocalDate billingStartDate) {
		this.billingStartDate = billingStartDate;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public ContractStatus getStatus() {
		return status;
	}
	public void setStatus(ContractStatus status) {
		this.status = status;
	}
	public Payment_Status getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Payment_Status paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public double getTotalAmountWithGst() {
		return TotalAmountWithGst;
	}
	public void setTotalAmountWithGst(double totalAmountWithGst) {
		TotalAmountWithGst = totalAmountWithGst;
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
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	
	
	
	
}
