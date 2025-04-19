package com.application.apa.controller;

import com.application.apa.Utility.FileStorageUtil;
import com.application.apa.model.VendorContract;
import com.application.apa.repository.VendorContractRepository;
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
@RequestMapping("/api/vendor-contracts")
public class VendorContractDocumentController {

    @Autowired
    private VendorContractRepository vendorContractRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @PostMapping("/{id}/uploadContractAgreement")
    public ResponseEntity<String> uploadContractAgreement(@PathVariable Long id,
                                                           @RequestParam("contractAgreement") MultipartFile contractAgreement) {
        try {
            VendorContract contract = vendorContractRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vendor Contract Not Found"));

            String path = fileStorageUtil.saveFile(contractAgreement, "vendor_contract_" + id);
            contract.setContractDocumentPath(path);
            vendorContractRepository.save(contract);

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
            VendorContract contract = vendorContractRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vendor Contract Not Found"));

            String path = fileStorageUtil.saveFile(poDocument, "vendor_po_" + id);
            contract.setPoDocumentPath(path);
            vendorContractRepository.save(contract);

            return ResponseEntity.ok("PO file saved at: " + path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving PO document: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/downloadContractDocument")
    public ResponseEntity<byte[]> downloadContractDocument(@PathVariable Long id) throws IOException {
        VendorContract contract = vendorContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor Contract Not Found"));

        Path path = Paths.get(contract.getContractDocumentPath());
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .body(fileBytes);
    }

    @GetMapping("/{id}/downloadPoDocument")
    public ResponseEntity<byte[]> downloadPoDocument(@PathVariable Long id) throws IOException {
        VendorContract contract = vendorContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor Contract Not Found"));

        Path path = Paths.get(contract.getPoDocumentPath());
        byte[] fileBytes = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + path.getFileName().toString() + "\"")
                .body(fileBytes);
    }
}
