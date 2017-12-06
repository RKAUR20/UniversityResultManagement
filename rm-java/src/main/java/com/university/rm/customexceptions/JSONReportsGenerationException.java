package com.university.rm.customexceptions;

public class JSONReportsGenerationException extends ApplicationExceptionFactory{

	public JSONReportsGenerationException(Exception ex) {
		// TODO Auto-generated constructor stub
		super(ex);
	}

	@Override
	public String getExceptionMessage() {
		// TODO Auto-generated method stub
		return "Error while publishing JSON reports. Please contact system administrator or try again later!";
	}

}
