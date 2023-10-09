package com.ffs.simplecashtransaction.exception;

public class BusinessRuleViolationException extends RuntimeException {
	
	private static final long serialVersionUID = 2970053786815792331L;

	public BusinessRuleViolationException(String message) {
        super(message);
    }
	
}
