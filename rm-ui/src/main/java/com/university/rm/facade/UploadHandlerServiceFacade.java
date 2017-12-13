package com.university.rm.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.university.rm.model.FileBucket;
import com.university.rm.model.Student;
import com.university.rm.service.FileHandlerService;
import com.university.rm.service.ResultCalculatorService;

@Component
public class UploadHandlerServiceFacade {
	
	final Logger logger = Logger.getLogger(UploadHandlerServiceFacade.class); 
	
	@Autowired
	private FileHandlerService fileHandlerService;

	@Autowired
	private ResultCalculatorService resultCalculatorService;
	
	public FileHandlerService getFileHandlerService() {
		return fileHandlerService;
	}

	public void setFileHandlerService(FileHandlerService fileHandlerService) {
		this.fileHandlerService = fileHandlerService;
	}

	public ResultCalculatorService getResultCalculatorService() {
		return resultCalculatorService;
	}

	public void setResultCalculatorService(ResultCalculatorService resultCalculatorService) {
		this.resultCalculatorService = resultCalculatorService;
	}

	
	public void handleUploadService(FileBucket fileBucket) {
		logger.debug("Passing control to service for convertXMLFileToStudents.");
		List<Student> students = getFileHandlerService().convertXMLFileToStudents(fileBucket);
		if (null != students && !students.isEmpty()) {
			logger.debug("Students received after marshalling XML :: " + students.size());
			logger.debug("Clear history :: Report and tables");
			clearHistoryData();
			logger.debug("Passing control to service for calculateStudentsResult.");
			getResultCalculatorService().calculateAndSaveStudentsResult(students);
			logger.debug("Passing control to service for createJSONReports.");
			getFileHandlerService().createJSONReports(students);
		}
			
	}

	private void clearHistoryData() {
		// TODO Auto-generated method stub
		getFileHandlerService().clearPreviousJSONReports();
		getResultCalculatorService().truncateTables();
	}
	
}
