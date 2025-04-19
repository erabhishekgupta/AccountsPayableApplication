package com.application.apa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.application.apa.dto.GstVerificationResponse;
import org.springframework.http.*;
@Service
public class GstVerificationService {

    @Value("${appyflow.gst.api.url}")
    private String apiUrl;

    @Value("${appyflow.gst.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public GstVerificationResponse verifyGst(String gstNumber) {
        String url = String.format("%s?gstNo=%s&key_secret=%s", apiUrl, gstNumber, apiKey);
        
        ResponseEntity<GstVerificationResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
