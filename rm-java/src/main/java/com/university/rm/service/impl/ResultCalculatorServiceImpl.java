package com.university.rm.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.university.rm.model.Status;
import com.university.rm.model.Student;
import com.university.rm.service.ResultCalculatorService;


@Service
public class ResultCalculatorServiceImpl implements ResultCalculatorService {

	final Logger logger = Logger.getLogger(ResultCalculatorServiceImpl.class);
	
	public void calculateStudentsResult(List<Student> students) {
		// TODO Auto-generated method stub
		
		logger.debug("Going to set result and total marks of students.");
		
		ExecutorService executor = Executors.newFixedThreadPool(5);

		for (Student student : students) {
			executor.submit(() -> {
				student.setStatus();
				student.setTotalMarks();
			});
		}

		executor.shutdown();

		try {
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("Result and total marks of student set.");
		logger.debug("Calculating Rank for pass students.");
		rankStudents(students);

	}
	
	private void rankStudents(List<Student> students) {
		for (int i = 0; i < students.size(); i++) {
			Student current = students.get(i);
			
			if (current.getStatus() == Status.PASS) {
				
				for (int j = i + 1; j < students.size(); j++) {
					Student jStudent = students.get(j);
					
					if (jStudent.getStatus() == Status.PASS) {
						if (current.getTotalMarks() < jStudent.getTotalMarks()) {
							current.setRank(current.getRank() + 1);
						} else {
							jStudent.setRank(jStudent.getRank() + 1);
						}
					}
					
				}
				
			}
		}
		
		logger.debug("Ranking students completed.");
	}

}
