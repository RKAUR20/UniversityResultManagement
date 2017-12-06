package com.university.rm.customexceptions;

public abstract class ApplicationExceptionFactory extends Exception {
	
	public ApplicationExceptionFactory(Exception ex) {
		// TODO Auto-generated constructor stub
		super(ex);
	}

	public ApplicationExceptionFactory(String exceptionMessage) {
		// TODO Auto-generated constructor stub
		super(exceptionMessage);
	}

	public abstract String getExceptionMessage();

}
