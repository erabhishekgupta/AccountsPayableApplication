package com.application.apa.Utility;

import com.application.apa.Enum.BillingFrequency;
import com.application.apa.Enum.PaymentMethod;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PDFGenerator {

    public static byte[] generateContractBillPdf(String vendorName, String customerName, String poReference, LocalDate startDate,
                                                 LocalDate endDate, BillingFrequency billingFrequency, Double recurringAmount,
                                                 LocalDate nextBillingDate, LocalDate dueDate, PaymentMethod paymentMethod,
                                                 String messageBody) {

        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

           
            BaseFont baseFont = BaseFont.createFont(
                    PDFGenerator.class.getClassLoader().getResource("font/NotoSans-Regular.ttf").getPath(),
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );

            Font titleFont = new Font(baseFont, 18, Font.BOLD);
            Font labelFont = new Font(baseFont, 12, Font.BOLD);
            Font textFont = new Font(baseFont, 12);
            Font smallFont = new Font(baseFont, 10);

            double cgstRate = 0.09;
            double sgstRate = 0.09;
            double totalAmount = recurringAmount + recurringAmount * (cgstRate + sgstRate);
            double cgstAmount = recurringAmount * cgstRate;
            double sgstAmount = recurringAmount * sgstRate;

            // Title
            Paragraph title = new Paragraph("Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Vendor & Contract Info Table
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setSpacingBefore(10f);
            infoTable.setSpacingAfter(10f);

            addCell(infoTable, "Vendor Name", labelFont);
            addCell(infoTable, vendorName, textFont);
            
            addCell(infoTable, "Customer Name", labelFont);
            addCell(infoTable, customerName, textFont);

            addCell(infoTable, "PO Reference", labelFont);
            addCell(infoTable, poReference, textFont);

            addCell(infoTable, "Contract Start Date", labelFont);
            addCell(infoTable, format(startDate), textFont);

            addCell(infoTable, "Contract End Date", labelFont);
            addCell(infoTable, format(endDate), textFont);

            addCell(infoTable, "Billing Frequency", labelFont);
            addCell(infoTable, billingFrequency.toString(), textFont);

            addCell(infoTable, "Due Date", labelFont);
            addCell(infoTable, format(dueDate), textFont);

            addCell(infoTable, "Amount (Before GST)", labelFont);
            addCell(infoTable, "₹" + String.format("%.2f", recurringAmount), textFont);

            addCell(infoTable, "CGST @ 9%", labelFont);
            addCell(infoTable, "₹" + String.format("%.2f", cgstAmount), textFont);

            addCell(infoTable, "SGST @ 9%", labelFont);
            addCell(infoTable, "₹" + String.format("%.2f", sgstAmount), textFont);

            addCell(infoTable, "Total Amount (After GST)", labelFont);
            addCell(infoTable, "₹" + String.format("%.2f", totalAmount), textFont);

            document.add(infoTable);

           
            Paragraph note = new Paragraph(messageBody, smallFont);
            note.setSpacingBefore(10);
            note.setSpacingAfter(20);
            document.add(note);

           
            Paragraph footer = new Paragraph(
                    "Thank you for your continued support.\nWe truly appreciate your business.",
                    labelFont
            );
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();
            return out.toByteArray();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void addCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(15);
        cell.setPadding(6f);
        table.addCell(cell);
    }

    private static String format(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }
}
