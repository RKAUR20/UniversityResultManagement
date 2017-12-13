package com.university.rm.service;

import java.io.File;
import java.util.List;

import com.university.rm.model.FileBucket;
import com.university.rm.model.Student;


public interface FileHandlerService {
	
	public List<Student> convertXMLFileToStudents(FileBucket fileBucket);
	
	public void createJSONReports(List<Student> students);

	public File getStudentReport(String studentName);

	void clearPreviousJSONReports();
}
