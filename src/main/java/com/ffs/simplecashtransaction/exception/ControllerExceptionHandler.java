package com.ffs.simplecashtransaction.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ffs.simplecashtransaction.dtos.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionDTO> handleDuplicateEntry(DataIntegrityViolationException e) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("User already registered", HttpStatus.BAD_REQUEST.toString());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	@ExceptionHandler(BusinessRuleViolationException.class)
	public ResponseEntity<ExceptionDTO> handleBusinessRuleViolation(BusinessRuleViolationException e) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST.toString());
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleNotFound(EntityNotFoundException e) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneralException(Exception e) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return ResponseEntity.internalServerError().body(exceptionDTO);
	}
}
