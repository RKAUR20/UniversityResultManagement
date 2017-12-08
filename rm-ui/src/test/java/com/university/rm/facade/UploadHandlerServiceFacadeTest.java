package com.university.rm.facade;

import org.junit.Before;
import org.junit.Test;

import com.university.rm.customexceptions.InputFileUnmarshalException;
import com.university.rm.customexceptions.JSONReportsGenerationException;
import com.university.rm.model.FileBucket;
import com.university.rm.model.Student;
import com.university.rm.service.FileHandlerService;
import com.university.rm.service.ResultCalculatorService;
import com.university.rm.service.impl.FileHandlerServiceImpl;

import org.mockito.Mock;
import org.mockito.Mockito;


public class UploadHandlerServiceFacadeTest {
	
	@Mock
	private FileHandlerService fileHandlerService;
	
	@Mock
	private ResultCalculatorService resultCalculatorService;
	
	private UploadHandlerServiceFacade uploadHandlerServiceFacade;
	
	@Before
	public void setUp() {
		fileHandlerService = new FileHandlerServiceImpl();
		uploadHandlerServiceFacade = new UploadHandlerServiceFacade();
	}
	
	@Test
	public void testHandleUploadService() throws InputFileUnmarshalException {
		Mockito.doNothing().when(fileHandlerService.convertXMLFileToStudents(Mockito.anyObject()));
		
		try {
			uploadHandlerServiceFacade.handleUploadService(Mockito.anyObject());
		} catch (InputFileUnmarshalException | JSONReportsGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Mockito.verify(fileHandlerService.convertXMLFileToStudents(Mockito.anyObject()));
		//Mockito.verify(resultCalculatorService.calculateStudentsResult(Mockito.anyList()));
		Mockito.verify(fileHandlerService.convertXMLFileToStudents(Mockito.anyObject()));
		
	}

}
