package com.university.rm.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StudentTest {

	private Student student;
	private List<Subject> passSubjectList;
	private List<Subject> failSubjectList;
	
	@Before
	public void setUp() {
		student = new Student();
		student.setName("Ram");
		student.setId(1101);
		
		passSubjectList = new ArrayList<>();
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
		passSubjectList.add(sub1);
		passSubjectList.add(sub2);
		passSubjectList.add(sub3);
		passSubjectList.add(sub4);
		passSubjectList.add(sub5);
		
		failSubjectList = new ArrayList<>();
		Subject sub6 = new Subject();
		sub6.setSubjectName("Maths");
		sub6.setMarks(35);
		Subject sub7 = new Subject();
		sub7.setSubjectName("English");
		sub7.setMarks(20);
		failSubjectList.add(sub6);
		failSubjectList.add(sub7);
	}
	
	/*@Test
	public void testSetTotalMarks() {
		student.setSubjects(passSubjectList);
		student.setTotalMarks();
		assertEquals(new Integer(345), student.getTotalMarks());
	}
	
	@Test
	public void testSetStatusPASS() {
		student.setSubjects(passSubjectList);
		student.setStatus();
		assertEquals(Status.PASS, student.getStatus());
	}
	
	@Test
	public void testSetStatusFAIL() {
		student.setSubjects(failSubjectList);
		student.setStatus();
		assertEquals(Status.FAIL, student.getStatus());
	}*/
	
}
