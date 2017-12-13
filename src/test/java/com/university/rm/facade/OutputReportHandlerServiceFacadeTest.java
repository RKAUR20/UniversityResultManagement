package com.university.rm.facade;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.university.rm.customexceptions.ReportNotFoundException;
import com.university.rm.service.FileHandlerService;
import com.university.rm.service.impl.FileHandlerServiceImpl;

public class OutputReportHandlerServiceFacadeTest {

	private FileHandlerService fileHandlerService;
	private OutputReportHandlerServiceFacade outputReportHandlerServiceFacade;
	
	@Before
	public void setUp() {
		fileHandlerService = Mockito.mock(FileHandlerServiceImpl.class);
		outputReportHandlerServiceFacade = new OutputReportHandlerServiceFacade();
		outputReportHandlerServiceFacade.setFileHandlerService(fileHandlerService);
	}
	
	@Test(expected = ReportNotFoundException.class)
	public void testHandleOutputReportServiceException() throws ReportNotFoundException {
		// Mock
		Mockito.when(fileHandlerService.getStudentReport(Matchers.anyString()))
				.thenThrow(new ReportNotFoundException(Matchers.anyString()));

		// Call
		outputReportHandlerServiceFacade.handleOutputReportService("Ram");
	}
	
	@Test
	public void testHandleOutputReportService() throws ReportNotFoundException {
		// Mock
		Mockito.when(fileHandlerService.getStudentReport(Matchers.anyString()))
				.thenReturn(Matchers.any());

		// Call
		outputReportHandlerServiceFacade.handleOutputReportService("Ram");
		
		//Verify
		Mockito.verify(fileHandlerService).getStudentReport("Ram");
	}
	
}
