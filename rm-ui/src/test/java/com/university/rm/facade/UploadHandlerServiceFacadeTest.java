package com.university.rm.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.university.rm.customexceptions.InputFileUnmarshalException;
import com.university.rm.customexceptions.JSONReportsGenerationException;
import com.university.rm.model.Student;
import com.university.rm.model.Subject;
import com.university.rm.service.FileHandlerService;
import com.university.rm.service.ResultCalculatorService;
import com.university.rm.service.impl.FileHandlerServiceImpl;
import com.university.rm.service.impl.ResultCalculatorServiceImpl;


public class UploadHandlerServiceFacadeTest {
	
	private FileHandlerService fileHandlerService;
	
	private ResultCalculatorService resultCalculatorService;
	
	private UploadHandlerServiceFacade uploadHandlerServiceFacade;

	private List<Student> students = new ArrayList<>();
	
	@Before
	public void setUp() {
		fileHandlerService = Mockito.mock(FileHandlerServiceImpl.class);
		resultCalculatorService = Mockito.mock(ResultCalculatorServiceImpl.class);
		uploadHandlerServiceFacade = new UploadHandlerServiceFacade();
		uploadHandlerServiceFacade.setFileHandlerService(fileHandlerService);
		uploadHandlerServiceFacade.setResultCalculatorService(resultCalculatorService);
		Student student = new Student();
		student.setName("Ram");
		student.setId(1101);
		
		Set<Subject> subjectList1 = new HashSet<>();
		Subject sub1 = new Subject();
		sub1.setSubjectName("Maths");
		sub1.setMarks(55);
		Subject sub2 = new Subject();
		sub2.setSubjectName("English");
		sub2.setMarks(58);
		Subject sub3 = new Subject();
		sub3.setSubjectName("History");
		sub3.setMarks(85);
		Subject sub4 = new Subject();
		sub4.setSubjectName("Science");
		sub4.setMarks(69);
		Subject sub5 = new Subject();
		sub5.setSubjectName("Punjabi");
		sub5.setMarks(78);
		subjectList1.add(sub1);
		subjectList1.add(sub2);
		subjectList1.add(sub3);
		subjectList1.add(sub4);
		subjectList1.add(sub5);
		student.setSubjects(subjectList1);
		
		Student student2 = new Student();
		student2.setId(102);
		student2.setName("Shyam");
		Set<Subject> subjectList2 = new HashSet<>();
		Subject sub6 = new Subject();
		sub6.setSubjectName("Maths");
		sub6.setMarks(35);
		Subject sub7 = new Subject();
		sub7.setSubjectName("English");
		sub7.setMarks(20);
		subjectList2.add(sub6);
		subjectList2.add(sub7);
		student2.setSubjects(subjectList2);
		
		students.add(student);
		students.add(student2);
	}
	
	@Test
	public void testHandleUploadService() throws InputFileUnmarshalException, JSONReportsGenerationException {

		//Mock
		Mockito.when(fileHandlerService.convertXMLFileToStudents(Mockito.anyObject())).thenReturn(students);
		Mockito.doNothing().when(resultCalculatorService).truncateTables();
		Mockito.doNothing().when(fileHandlerService).createJSONReports(Mockito.anyList());
		Mockito.doNothing().when(fileHandlerService).clearPreviousJSONReports();
		Mockito.doNothing().when(resultCalculatorService).calculateAndSaveStudentsResult(Mockito.anyList());
		
		//Call
		uploadHandlerServiceFacade.handleUploadService(Mockito.anyObject());

		//Verify
		Mockito.verify(fileHandlerService).createJSONReports(Mockito.anyList());
		Mockito.verify(resultCalculatorService).calculateAndSaveStudentsResult(Mockito.anyList());
		Mockito.verify(resultCalculatorService).truncateTables();

	}
	
	
	@Test(expected = InputFileUnmarshalException.class)
	public void testInputFileUnmarshalException() throws InputFileUnmarshalException, JSONReportsGenerationException {

		// Mock
		Mockito.when(fileHandlerService.convertXMLFileToStudents(Mockito.anyObject()))
				.thenThrow(new InputFileUnmarshalException(new Exception()));

		// Call
		uploadHandlerServiceFacade.handleUploadService(Mockito.anyObject());
	}
	
}
