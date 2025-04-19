package com.application.apa.Utility;

import java.io.IOException;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;

@Component
public class FileStorageUtil {
	 //${file.upload-dir}
     //private final String uploadDir = "/home/shivit/Desktop/contract&PoSave";
	
	    @Value("${file.upload-dir}")
	    private String uploadDir;
	
	public String saveFile(MultipartFile file, String filenamePrefix) throws IOException{
		if(file==null|| file.isEmpty()) {
			throw new RuntimeException("upload file is empty or missing");
		}
		
		System.out.println("Original Filename: " + file.getOriginalFilename());
		System.out.println("Filename prefix: " + filenamePrefix);
		
		String originalFileName = file.getOriginalFilename();
		if(originalFileName==null || originalFileName.isBlank()) {
			throw new RuntimeException("File name is invalid");
		}
		String newFileName = filenamePrefix + "_" + originalFileName;
		
		Path directoryPath = Paths.get(uploadDir);
		
		Path fullFilePath = directoryPath.resolve(newFileName);
		
		Files.createDirectories(directoryPath);	
		
		Files.write(fullFilePath, file.getBytes());
		
		System.out.println("Saved File to : " + fullFilePath.toString());
		
		return fullFilePath.toString();
	}
}
