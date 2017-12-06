package com.university.rm.customexceptions;

public class InvalidInputFileException extends ApplicationExceptionFactory{

	public InvalidInputFileException() {
		super("File being uploaded does not have valid file extension. Please upload XML file only.");
	}

	@Override
	public String getExceptionMessage() {
		// TODO Auto-generated method stub
		return "File being uploaded does not have valid file extension. Please upload XML file only.";
	}

}
