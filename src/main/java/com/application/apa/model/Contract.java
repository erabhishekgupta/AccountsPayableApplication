package com.application.apa.model;

import java.time.LocalDate;

import com.application.apa.Enum.BillingFrequency;
import com.application.apa.Enum.ContractStatus;
import com.application.apa.Enum.PaymentMethod;
import com.application.apa.Enum.Payment_Status;
import com.application.apa.Enum.ReferenceSource;
import com.application.apa.Enum.ReferenceType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Abstract base class for all contract types (VendorContract,
 * CustomerContract). Contains common attributes and behavior.
 */
@MappedSuperclass
public abstract class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate startDate;
	private LocalDate endDate;

	private String poReference;
	private Double recurringBillAmount;
	private LocalDate billingStartDate;

	@Enumerated(EnumType.STRING)
	private BillingFrequency billingFrequency;

	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	@Enumerated(EnumType.STRING)
	private ContractStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status")
	private Payment_Status paymentStatus;

	private double totalAmountWithGst;

	@Column(name = "contract_document_path")
	private String contractDocumentPath;

	@Column(name = "po_document_path")
	private String poDocumentPath;
	
	@Enumerated(EnumType.STRING)
	private ReferenceSource referenceSource;
	
	@Enumerated(EnumType.STRING)
	private ReferenceType referenceType;

	// ===== Getters and Setters =====

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		this.status = status;
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

	/**
	 * Check if contract is expiring in the next N days
	 */
	public boolean isExpiringSoon(int daysThreshold) {
		return endDate != null && endDate.isBefore(LocalDate.now().plusDays(daysThreshold));
	}

	public Contract(LocalDate startDate, LocalDate endDate, String poReference, Double recurringBillAmount,
			LocalDate billingStartDate, BillingFrequency billingFrequency, PaymentMethod paymentMethod,
			ContractStatus status, Payment_Status paymentStatus, double totalAmountWithGst, String contractDocumentPath,
			String poDocumentPath, ReferenceSource referenceSource, ReferenceType referenceType) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.poReference = poReference;
		this.recurringBillAmount = recurringBillAmount;
		this.billingStartDate = billingStartDate;
		this.billingFrequency = billingFrequency;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.paymentStatus = paymentStatus;
		this.totalAmountWithGst = totalAmountWithGst;
		this.contractDocumentPath = contractDocumentPath;
		this.poDocumentPath = poDocumentPath;
		this.referenceType = referenceType;
		this.referenceSource = referenceSource;
	}
	
	public Contract() {}

}
