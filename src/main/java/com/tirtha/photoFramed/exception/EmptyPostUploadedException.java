package com.tirtha.photoFramed.exception;

public class EmptyPostUploadedException extends RuntimeException {

	
	private static final long serialVersionUID = 7130316750068441258L;

	public EmptyPostUploadedException() {
		super();
	}
	
	public EmptyPostUploadedException(String message) {
		super(message);
	}
}
