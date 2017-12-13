package com.university.rm.customexceptions;

public class InputFileUnmarshalException extends RuntimeException{

	private static final long serialVersionUID = 4455036564683701213L;

	public InputFileUnmarshalException(Exception ex) {
		// TODO Auto-generated constructor stub
		super(ex);
	}

	public String getExceptionMessage() {
		// TODO Auto-generated method stub
		return "Input XML could not be marshalled. Please check its content. Download sample for more details";
	}


}
