package com.university.rm.customexceptions;

public class InputFileUnmarshalException extends ApplicationExceptionFactory{

	public InputFileUnmarshalException(Exception ex) {
		// TODO Auto-generated constructor stub
		super(ex);
	}

	@Override
	public String getExceptionMessage() {
		// TODO Auto-generated method stub
		return "Input XML could not be marshalled. Please check its content. Download sample for more details";
	}


}
