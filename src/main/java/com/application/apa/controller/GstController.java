package com.application.apa.controller;


import com.application.apa.dto.GstVerificationResponse;
import com.application.apa.service.GstVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gst")
public class GstController {

    @Autowired
    private GstVerificationService gstVerificationService;
    
    //api endpoint to verify gst 
    @GetMapping("/verify")
    public ResponseEntity<String> verifyGst(@RequestParam String gstNumber) {
        GstVerificationResponse response = gstVerificationService.verifyGst(gstNumber);
        if (response == null || response.isError() || response.getTaxpayerInfo() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("GST verification failed: " + response.getMessage());
        }
        String pan = response.getTaxpayerInfo().getPanNo();
        return ResponseEntity.ok(pan);
    }
}


