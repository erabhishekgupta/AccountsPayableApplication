package com.application.apa.controller;

import com.application.apa.Utility.FileStorageUtil;
import com.application.apa.model.CustomerContract;
import com.application.apa.repository.CustomerContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/customer-contracts")
public class CustomerContractDocumentController {

    @Autowired
    private CustomerContractRepository customerContractRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @PostMapping("/{id}/uploadContractAgreement")
    public ResponseEntity<String> uploadContractAgreement(@PathVariable Long id,
                                                           @RequestParam("contractAgreement") MultipartFile contractAgreement) {
        try {
            CustomerContract contract = customerContractRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer Contract Not Found"));

            String path = fileStorageUtil.saveFile(contractAgreement, "customer_contract_" + id);
            contract.setContractDocumentPath(path);
            customerContractRepository.save(contract);

            return ResponseEntity.ok("Contract file saved at: " + path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving contract document: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/uploadPoDocument")
    public ResponseEntity<String> uploadPoDocument(@PathVariable Long id,
                                                   @RequestParam("poDocument") MultipartFile poDocument) {
        try {
            CustomerContract contract = customerContractRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer Contract Not Found"));

            String path = fileStorageUtil.saveFile(poDocument, "customer_po_" + id);
            contract.setPoDocumentPath(path);
            customerContractRepository.save(contract);

            return ResponseEntity.ok("PO file saved at: " + path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving PO document: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/downloadContractDocument")
    public ResponseEntity<byte[]> downloadContractDocument(@PathVariable Long id) throws IOException {
        CustomerContract contract = customerContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Contract Not Found"));

        Path path = Paths.get(contract.getContractDocumentPath());
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .body(fileBytes);
    }

    @GetMapping("/{id}/downloadPoDocument")
    public ResponseEntity<byte[]> downloadPoDocument(@PathVariable Long id) throws IOException {
        CustomerContract contract = customerContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Contract Not Found"));

        Path path = Paths.get(contract.getPoDocumentPath());
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .body(fileBytes);
    }
}

