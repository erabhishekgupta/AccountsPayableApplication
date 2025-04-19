package com.application.apa.service;

import com.application.apa.Utility.FileStorageUtil;
import com.application.apa.model.VendorContract;
import com.application.apa.repository.VendorContractRepository;
import com.application.apa.serviceInterface.VendorContractDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VendorContractDocumentServiceImpl implements VendorContractDocumentService {

    @Autowired
    private VendorContractRepository vendorContractRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Override
    public String uploadContractAgreement(Long id, MultipartFile file) throws IOException {
        VendorContract contract = vendorContractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vendor Contract Not Found"));
        String path = fileStorageUtil.saveFile(file, "vendor_contract_" + id);
        contract.setContractDocumentPath(path);
        vendorContractRepository.save(contract);
        return path;
    }

    @Override
    public String uploadPoDocument(Long id, MultipartFile file) throws IOException {
        VendorContract contract = vendorContractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vendor Contract Not Found"));
        String path = fileStorageUtil.saveFile(file, "vendor_po_" + id);
        contract.setPoDocumentPath(path);
        vendorContractRepository.save(contract);
        return path;
    }

    @Override
    public byte[] downloadContractAgreement(Long id) throws IOException {
        VendorContract contract = vendorContractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vendor Contract Not Found"));
        Path path = Paths.get(contract.getContractDocumentPath());
        return Files.readAllBytes(path);
    }

    @Override
    public byte[] downloadPoDocument(Long id) throws IOException {
        VendorContract contract = vendorContractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vendor Contract Not Found"));
        Path path = Paths.get(contract.getPoDocumentPath());
        return Files.readAllBytes(path);
    }
}

