package com.university.rm.customexceptions;

public class ReportNotFoundException extends ApplicationExceptionFactory {

	private String studentName;

	public ReportNotFoundException(String studentName) {
		super(studentName);
		this.studentName = studentName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getExceptionMessage() {
		// TODO Auto-generated method stub
		return "Report of Student "+ studentName +" not found. Please enter valid student name or upload the new data!!";
	}

}
