package com.application.apa.service;

import com.application.apa.Utility.FileStorageUtil;
import com.application.apa.model.CustomerContract;
import com.application.apa.repository.CustomerContractRepository;
import com.application.apa.serviceInterface.CustomerContractDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CustomerContractDocumentServiceImpl implements CustomerContractDocumentService {

    @Autowired
    private CustomerContractRepository customerContractRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Override
    public String uploadContractAgreement(Long id, MultipartFile file) throws IOException {
        CustomerContract contract = customerContractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer Contract Not Found"));
        String path = fileStorageUtil.saveFile(file, "customer_contract_" + id);
        contract.setContractDocumentPath(path);
        customerContractRepository.save(contract);
        return path;
    }

    @Override
    public String uploadPoDocument(Long id, MultipartFile file) throws IOException {
        CustomerContract contract = customerContractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer Contract Not Found"));
        String path = fileStorageUtil.saveFile(file, "customer_po_" + id);
        contract.setPoDocumentPath(path);
        customerContractRepository.save(contract);
        return path;
    }

    @Override
    public byte[] downloadContractAgreement(Long id) throws IOException {
        CustomerContract contract = customerContractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer Contract Not Found"));
        Path path = Paths.get(contract.getContractDocumentPath());
        return Files.readAllBytes(path);
    }

    @Override
    public byte[] downloadPoDocument(Long id) throws IOException {
        CustomerContract contract = customerContractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer Contract Not Found"));
        Path path = Paths.get(contract.getPoDocumentPath());
        return Files.readAllBytes(path);
    }
}
