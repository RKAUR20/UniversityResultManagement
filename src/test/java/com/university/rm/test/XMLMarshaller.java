package com.university.rm.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.university.rm.model.Student;
import com.university.rm.model.Students;
import com.university.rm.model.Subject;

public class XMLMarshaller {
	
	public static void main(String [] args) throws JAXBException, FileNotFoundException {
		Students students = new Students();
		
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
		
		Random rand = new Random();
		students.setStudents(new ArrayList<>());
		for(int i=0 ; i < 10; i++) {
			Student s = new Student();
			s.setId(i+100);
			s.setName("Student "+i);
			s.setCourse("Course " + rand.nextInt(5));
			Set<Subject> subjects = new HashSet<>();
			
			for(int j=0; j<5; j++) {
				Subject sub = new Subject();
				//sub.setSubjectId(j);
				sub.setSubjectName(subjectArray[rand.nextInt(10)]);
				sub.setMarks(rand.nextInt(100) + 30);
				subjects.add(sub);
			}
			s.setSubjects(subjects);
			students.getStudents().add(s);
		}
		
		System.out.println(students.getStudents().size());
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.marshal(students, new FileOutputStream("C:\\Users\\\\rkau23\\Students.xml"));  
		
	}

}
