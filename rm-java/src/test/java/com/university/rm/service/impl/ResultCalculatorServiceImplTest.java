package com.university.rm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.university.rm.dao.StudentDAO;
import com.university.rm.dao.impl.StudentDAOImpl;
import com.university.rm.model.Student;
import com.university.rm.model.Subject;

public class ResultCalculatorServiceImplTest {

	private ResultCalculatorServiceImpl resultCalculatorService;
	private StudentDAO studentDAO = null;
	private List<Student> students = new ArrayList<>();
	
	@Before
	public void setUp() {
		studentDAO =  Mockito.mock(StudentDAOImpl.class);
		resultCalculatorService = new ResultCalculatorServiceImpl();
		resultCalculatorService.setStudentDAO(studentDAO);
	}
	
	@Test
	public void testTruncateTables() {
		resultCalculatorService.truncateTables();
		Mockito.verify(studentDAO).deleteAllData();
	}
	
	@Test
	public void testCalculateAndSaveStudentsResult() {
		Mockito.doNothing().when(studentDAO).addStudents(Mockito.anyList());
		setRandomStudents();
		resultCalculatorService.calculateAndSaveStudentsResult(students);
		Mockito.verify(studentDAO).addStudents(Mockito.anyList());;
	}

	private void setRandomStudents() {
		Random rand = new Random();
		for(int i=0 ; i < 10; i++) {
			Student s = new Student();
			s.setId(i+100);
			s.setName("Student "+i);
			s.setCourse("Course " + rand.nextInt(5));
			Set<Subject> subjects = new HashSet<>();
			
			for(int j=0; j<5; j++) {
				Subject sub = new Subject();
				//sub.setSubjectId(j);
				sub.setSubjectName("Subject " + j);
				sub.setMarks(rand.nextInt(100) + 30);
				subjects.add(sub);
			}
			s.setSubjects(subjects);
			students.add(s);
		}
	}
	
	
}
