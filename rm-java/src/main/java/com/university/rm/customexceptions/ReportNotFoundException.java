package com.university.rm.customexceptions;

public class ReportNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8709972876090330158L;
	private String studentName;

	public ReportNotFoundException(String studentName) {
		super(studentName);
		this.studentName = studentName;
		// TODO Auto-generated constructor stub
	}

	public String getExceptionMessage() {
		// TODO Auto-generated method stub
		return "Report of Student "+ studentName +" not found. Please enter valid student name or upload the new data!!";
	}

}
