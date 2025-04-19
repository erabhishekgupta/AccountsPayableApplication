package com.application.apa.serviceInterface;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


public interface VendorContractDocumentService {
	String uploadContractAgreement(Long id, MultipartFile file) throws IOException;
    String uploadPoDocument(Long id, MultipartFile file) throws IOException;
    byte[] downloadContractAgreement(Long id) throws IOException;
    byte[] downloadPoDocument(Long id) throws IOException;
}
