package com.university.rm.facade;

import java.io.File;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.university.rm.customexceptions.ReportNotFoundException;
import com.university.rm.service.FileHandlerService;


public class OutputReportHandlerServiceFacade {
	
final Logger logger = Logger.getLogger(OutputReportHandlerServiceFacade.class); 
	
	@Autowired
	FileHandlerService fileHandlerService;
	
	public File handleOutputReportService(String studentName) throws ReportNotFoundException {
		logger.debug("Passing control to service for getting student report.");
		return fileHandlerService.getStudentReport(studentName);
	}

}
