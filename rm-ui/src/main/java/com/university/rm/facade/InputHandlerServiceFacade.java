package com.university.rm.facade;

import java.io.File;
import java.util.List;
import org.apache.log4j.Logger;
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
	
	final Logger logger = Logger.getLogger(InputHandlerServiceFacade.class); 
	
	@Autowired
	FileHandlerService fileHandlerService;
	
	@Autowired
	ResultCalculatorService resultCalculatorService;
	
	public void handleInputFromUI(FileBucket fileBucket) throws InputFileUnmarshalException, JSONReportsGenerationException {
		logger.debug("Passing control to service for convertXMLFileToStudents.");
		List<Student> students = fileHandlerService.convertXMLFileToStudents(fileBucket);
		logger.debug("Students received after marshalling XML :: " + students.size());
		if (null != students && !students.isEmpty()) {
			logger.debug("Passing control to service for calculateStudentsResult.");
			resultCalculatorService.calculateStudentsResult(students);
			logger.debug("Passing control to service for createJSONReports.");
			fileHandlerService.createJSONReports(students);
		}
			
	}
	
	public File handleOutput(String studentName) throws ReportNotFoundException {
		logger.debug("Passing control to service for getting student report.");
		return fileHandlerService.getStudentReport(studentName);
	}

}
