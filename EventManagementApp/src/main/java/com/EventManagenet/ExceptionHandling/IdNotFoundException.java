package com.EventManagenet.ExceptionHandling;

public class IdNotFoundException extends RuntimeException {
	public IdNotFoundException(String message) {
		super(message);
	}
}

