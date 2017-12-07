package com.university.rm.service.impl;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.university.rm.model.Status;
import com.university.rm.model.Student;
import com.university.rm.service.ResultCalculatorService;


@Service
public class ResultCalculatorServiceImpl implements ResultCalculatorService {

	public void calculateStudentsResult(List<Student> students) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(5);

		// all jobs are submitted sequentially, but only 5 jobs execute concurrently at a time
		for(Student student : students) {
			executor.submit(new Runnable() {
				@Override
				public void run() {
					student.setStatus();
					student.setTotalMarks();
				}
			});
		}
		
		executor.shutdown();
        // Wait until all threads are finish
        try {
			executor.awaitTermination(50000, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void rankStudents(List<Student> students) {
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
	}

}
