package com.university.rm.facade;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void handleInputFromUI(FileBucket fileBucket) {
		List<Student> students = null;
		try {
			students = fileHandlerService.convertXMLFileToStudents(fileBucket);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null != students && !students.isEmpty()) {
			resultCalculatorService.calculateStudentsResult(students);
			try {
				fileHandlerService.createJSONReports(students);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public File handleOutput(String studentName) {
		return fileHandlerService.getStudentReport(studentName);
	}

}
