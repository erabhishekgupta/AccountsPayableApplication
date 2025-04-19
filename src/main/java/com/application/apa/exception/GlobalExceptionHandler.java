package com.application.apa.exception;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
		return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(EmailSendException.class)
	public ResponseEntity<?> handleEmailException(EmailSendException ex) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to Send Email : " + ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleEmailException(Exception ex) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong : " + ex.getMessage());
	}

	private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		body.put("error", status.getReasonPhrase());
		body.put("message", message);
		return new ResponseEntity<>(body, status);

	}
}
