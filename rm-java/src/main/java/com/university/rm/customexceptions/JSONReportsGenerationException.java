package com.university.rm.customexceptions;

public class JSONReportsGenerationException extends RuntimeException{

	private static final long serialVersionUID = 459655657939581358L;

	public JSONReportsGenerationException(Exception ex) {
		// TODO Auto-generated constructor stub
		super(ex);
	}

	public String getExceptionMessage() {
		// TODO Auto-generated method stub
		return "Error while publishing JSON reports. Please contact system administrator or try again later!";
	}

}
