package com.yogeshnagar.rover.common.exceptions;

public class ProcessingException extends RuntimeException {

	public ProcessingException(String msg, Exception e) {
		super(msg, e);
	}
	
}
