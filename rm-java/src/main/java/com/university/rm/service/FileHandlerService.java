package com.university.rm.service;

import java.io.File;
import java.util.List;

import com.university.rm.customexceptions.InputFileUnmarshalException;
import com.university.rm.customexceptions.JSONReportsGenerationException;
import com.university.rm.customexceptions.ReportNotFoundException;
import com.university.rm.model.FileBucket;
import com.university.rm.model.Student;


public interface FileHandlerService {
	
	public List<Student> convertXMLFileToStudents(FileBucket fileBucket) throws InputFileUnmarshalException;
	
	public void createJSONReports(List<Student> students) throws JSONReportsGenerationException;

	public File getStudentReport(String studentName) throws ReportNotFoundException;

	void clearPreviousJSONReports();
}
