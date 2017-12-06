package com.university.rm.facade;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.rm.customexceptions.InputFileUnmarshalException;
import com.university.rm.customexceptions.JSONReportsGenerationException;
import com.university.rm.customexceptions.ReportNotFoundException;
import com.university.rm.model.FileBucket;
import com.university.rm.model.Student;
import com.university.rm.service.FileHandlerService;
import com.university.rm.service.ResultCalculatorService;

@Service
public class InputHandlerServiceFacade {
	
	@Autowired
	FileHandlerService fileHandlerService;
	
	@Autowired
	ResultCalculatorService resultCalculatorService;
	
	public void handleInputFromUI(FileBucket fileBucket) throws InputFileUnmarshalException, JSONReportsGenerationException {
		List<Student> students = fileHandlerService.convertXMLFileToStudents(fileBucket);
		
		if (null != students && !students.isEmpty()) {
			resultCalculatorService.calculateStudentsResult(students);
			fileHandlerService.createJSONReports(students);
		}
			
	}
	
	public File handleOutput(String studentName) throws ReportNotFoundException {
		return fileHandlerService.getStudentReport(studentName);
	}

}
