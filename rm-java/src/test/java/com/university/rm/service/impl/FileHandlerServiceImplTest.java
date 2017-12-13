package com.university.rm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.university.rm.customexceptions.JSONReportsGenerationException;
import com.university.rm.customexceptions.ReportNotFoundException;
import com.university.rm.model.Student;
import com.university.rm.model.Subject;
import com.university.rm.service.FileHandlerService;

public class FileHandlerServiceImplTest {
	
	private FileHandlerService fileHandlerService;
	
	@Before
	public void setUp() {
		fileHandlerService = new FileHandlerServiceImpl();
	}
	
	@Test(expected = ReportNotFoundException.class)
	public void testGetStudentReport() throws ReportNotFoundException {
		fileHandlerService.getStudentReport("Ram");
	}
	
	@Test(timeout=1000)
	public void testCreateJSONReport() throws JSONReportsGenerationException {
		
		List<Student> students = this.getRandomStudentsList(1000);
		
		fileHandlerService.createJSONReports(students);
	}
	
	@After
	public void after() {
		fileHandlerService.clearPreviousJSONReports();
	}

	private List<Student> getRandomStudentsList(int n) {
		Random rand = new Random();
		
		String [] subjectArray = new String [10];
		subjectArray[0] = "Hindi";
		subjectArray[1] = "English";
		subjectArray[2] = "Maths";
		subjectArray[3] = "Punjabi";
		subjectArray[4] = "Science";
		subjectArray[5] = "SSt";
		subjectArray[6] = "Physics";
		subjectArray[7] = "Chemistry";
		subjectArray[8] = "Biology";
		subjectArray[9] = "Computer";
		
		List<Student> students = new ArrayList<>();
		for(int i=0 ; i < n; i++) {
			Student s = new Student();
			s.setId(i+100);
			s.setName("Student "+i);
			s.setCourse("Course " + rand.nextInt(5));
			Set<Subject> subjects = new HashSet<>();
			
			for (int j = 0; j < 5; j++) {
				Subject sub = new Subject();
				sub.setSubjectName(subjectArray[rand.nextInt(10)]);
				sub.setMarks(rand.nextInt(100) + 30);
				subjects.add(sub);
			}
			s.setSubjects(subjects);
			students.add(s);
			
		}
		return students;
	}

}
