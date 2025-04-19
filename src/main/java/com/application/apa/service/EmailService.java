package com.application.apa.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.application.apa.Enum.BillingFrequency;
import com.application.apa.Utility.BillingUtils;
import com.application.apa.Utility.PDFGenerator;
import com.application.apa.exception.EmailSendException;
import com.application.apa.model.CustomerContract;
import com.application.apa.model.VendorContract;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private TemplateEngine templateEngine;

    // Send Customer Reminder Email
    public void sendCustomerRemainder(String to, String subject, CustomerContract contract) {
        Context context = new Context();
        context.setVariable("vendorName", contract.getVendor().getName());
        context.setVariable("poReference", contract.getPoReference());
        context.setVariable("endDate", contract.getEndDate());
        context.setVariable("customerName", contract.getCustomer().getName());
        
        String htmlContent = templateEngine.process("email-customer-reminder.html", context);

        String messageBody = "This is a gentle reminder to make the pending payment. Please settle the invoice before the contract expiry date.";

        sendEmailWithInvoice(to, subject, htmlContent, contract, messageBody);
    }

    // Send Vendor Reminder Email
    public void sendVendorRemainder(String to, String subject, VendorContract contract) {
        Context context = new Context();
        context.setVariable("vendorName", contract.getVendor().getName());
        context.setVariable("poReference", contract.getPoReference());
        context.setVariable("endDate", contract.getEndDate());
        context.setVariable("customerName", contract.getCustomer().getName());
        
        String htmlContent = templateEngine.process("email-vendor-reminder.html", context);

        String messageBody = "This is a notification that the contract is about to expire. The customer has a pending payment.";

        sendEmailWithInvoice(to, subject, htmlContent, contract, messageBody);
    }

    // Helper method to send email with invoice
    private void sendEmailWithInvoice(String to, String subject, String htmlContent, VendorContract contract, String messageBody) {
        LocalDate lastBillingDate = contract.getStartDate();
        BillingFrequency frequency = contract.getBillingFrequency();
        LocalDate endDate = contract.getEndDate();
        LocalDate nextBillingDate = BillingUtils.calculateNextbillingDate(lastBillingDate, frequency);
        LocalDate dueDate = BillingUtils.calculateDueDate(endDate);

        byte[] pdfData = PDFGenerator.generateContractBillPdf(
            contract.getVendor().getName(),
            contract.getCustomer().getName(),
            contract.getPoReference(),
            contract.getStartDate(),
            contract.getEndDate(),
            contract.getBillingFrequency(),
            contract.getRecurringBillAmount(),
            nextBillingDate,
            dueDate,
            contract.getPaymentMethod(),
            messageBody
        );

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.addAttachment("contract-invoice.pdf", new ByteArrayResource(pdfData));

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailSendException("Failed to send email to " + to + " : " + e.getMessage());
        }
    }
    
    // Helper method to send email with invoice
    private void sendEmailWithInvoice(String to, String subject, String htmlContent, CustomerContract contract, String messageBody) {
        LocalDate lastBillingDate = contract.getStartDate();
        BillingFrequency frequency = contract.getBillingFrequency();
        LocalDate endDate = contract.getEndDate();
        LocalDate nextBillingDate = BillingUtils.calculateNextbillingDate(lastBillingDate, frequency);
        LocalDate dueDate = BillingUtils.calculateDueDate(endDate);

        byte[] pdfData = PDFGenerator.generateContractBillPdf(
            contract.getVendor().getName(),
            contract.getCustomer().getName(),
            contract.getPoReference(),
            contract.getStartDate(),
            contract.getEndDate(),
            contract.getBillingFrequency(),
            contract.getRecurringBillAmount(),
            nextBillingDate,
            dueDate,
            contract.getPaymentMethod(),
            messageBody
        );

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            helper.addAttachment("contract-invoice.pdf", new ByteArrayResource(pdfData));

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new EmailSendException("Failed to send email to " + to + " : " + e.getMessage());
        }
    }
}
