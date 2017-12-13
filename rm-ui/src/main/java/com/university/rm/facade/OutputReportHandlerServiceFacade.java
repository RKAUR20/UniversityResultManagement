package com.university.rm.facade;

import java.io.File;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.rm.customexceptions.ReportNotFoundException;
import com.university.rm.service.FileHandlerService;

@Service
public class OutputReportHandlerServiceFacade {
	
final Logger logger = Logger.getLogger(OutputReportHandlerServiceFacade.class); 
	
	@Autowired
	private FileHandlerService fileHandlerService;
	
	public FileHandlerService getFileHandlerService() {
		return fileHandlerService;
	}

	public void setFileHandlerService(FileHandlerService fileHandlerService) {
		this.fileHandlerService = fileHandlerService;
	}

	public File handleOutputReportService(String studentName) throws ReportNotFoundException {
		logger.debug("Passing control to service for getting student report.");
		return getFileHandlerService().getStudentReport(studentName);
	}

}
