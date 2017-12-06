package com.university.rm.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.university.rm.model.Status;
import com.university.rm.model.Student;
import com.university.rm.service.ResultCalculatorService;


@Service
public class ResultCalculatorServiceImpl implements ResultCalculatorService {

	public void calculateStudentsResult(List<Student> students) {
		// TODO Auto-generated method stub
		List<Student> passStudents = new ArrayList<Student>();
		for(Student student : students) {
			student.setStatus();
			if(student.getStatus() == Status.PASS) {
				passStudents.add(student);
			}
			student.setTotalMarks();
		}
		
	}

}
