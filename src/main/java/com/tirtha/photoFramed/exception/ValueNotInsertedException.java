package com.tirtha.photoFramed.exception;

public class ValueNotInsertedException extends RuntimeException {

	
	private static final long serialVersionUID = 7130316750068441258L;

	public ValueNotInsertedException() {
		super();
	}
	
	public ValueNotInsertedException(String message) {
		super(message);
	}
}
