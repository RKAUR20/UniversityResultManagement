package com.university.rm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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
	Student firstStudent = null;
	int firstStudentMarks = 0;
	int failStudents = 0;
	
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
	
	/*@Test
	public void testCalculateAndSaveStudentsResult() {
		
		//Mock
		Mockito.doNothing().when(studentDAO).addStudents(Mockito.anyList());
		setRandomStudents(10);
		
		//Call
		resultCalculatorService.calculateAndSaveStudentsResult(students);
		
		//Verify
		Mockito.verify(studentDAO).addStudents(Mockito.anyList());

		List<Student> passStudents = students.stream().filter(student -> null != student.getRank())
				.collect(Collectors.toList());
		assertEquals(students.size() - failStudents, passStudents.size());
		Collections.sort(passStudents, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				return o1.getRank().compareTo(o2.getRank());
			}
		});
		passStudents.forEach(System.out::println);
		assertEquals(firstStudent, passStudents.get(0));
	}*/
	
	/*@Test(timeout=10000)
	public void testPerformance() {
		// Mock
		Mockito.doNothing().when(studentDAO).addStudents(Mockito.anyList());
		setRandomStudents(1000);

		// Call
		resultCalculatorService.calculateAndSaveStudentsResult(students);
	}*/

	private void setRandomStudents(int n) {
		Random rand = new Random();
		
		for(int i=0 ; i < n; i++) {
			Student s = new Student();
			s.setId(i+100);
			s.setName("Student "+i);
			s.setCourse("Course " + rand.nextInt(5));
			
			int totalMarks = 0;
			boolean fail = false;
			Set<Subject> subjects = new HashSet<>();
			
			for (int j = 0; j < 5; j++) {
				Subject sub = new Subject();
				sub.setSubjectName("Subject " + j);
				sub.setMarks(rand.nextInt(100) + 30);
				subjects.add(sub);
				if (sub.getMarks() < 35) {
					failStudents++;
					fail = true;
				}
				totalMarks = totalMarks + sub.getMarks();
			}
			s.setSubjects(subjects);
			students.add(s);
			
			if(!fail && (firstStudent == null || firstStudentMarks < totalMarks)) {
				firstStudent = s;
				firstStudentMarks = totalMarks;
			}
		}
		
	}
	
	
}
