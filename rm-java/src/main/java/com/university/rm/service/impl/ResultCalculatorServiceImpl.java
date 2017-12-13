package com.university.rm.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.rm.dao.StudentDAO;
import com.university.rm.model.Status;
import com.university.rm.model.Student;
import com.university.rm.service.ResultCalculatorService;

@Service
public class ResultCalculatorServiceImpl implements ResultCalculatorService {

	final Logger logger = Logger.getLogger(ResultCalculatorServiceImpl.class);
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public void calculateAndSaveStudentsResult(List<Student> students) {
		// TODO Auto-generated method stub

		//Save Students data
		getStudentDAO().addStudents(students);
		
		this.calculateStudentsResult(students);
	}
	

	private void calculateStudentsResult(List<Student> students) {
		// TODO Auto-generated method stub
		logger.debug("Going to set result and total marks of students.");

		ExecutorService executor = Executors.newFixedThreadPool(5);

		List<Future<Student>> futureStudentList = new ArrayList<>();

		students.stream().forEach(student -> futureStudentList.add(executor.submit(() -> {
			logger.debug(
					Thread.currentThread().getName() + " executing status and total marks of " + student.getName());
			student.setStatus();
			student.setTotalMarks();
			return student;
		})));

		try {
			rankStudents(futureStudentList);
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		executor.shutdown();

		try {
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("Result and total marks of student set.");
	}


	/* 
	 * This method calculate rank of students. 
	 * As soon as future student object is received, 
	 * its total marks are compared with previously executed future objects (calculatedRankList), and
	 * after calculating its rank, this object is also added to calculatedRankList.
	 * */
	private void rankStudents(List<Future<Student>> futureStudentList) throws InterruptedException, ExecutionException {

		List<Student> calculatedRankList = new ArrayList<>();

		for (Future<Student> currentFuture : futureStudentList) {

			Student current = currentFuture.get();
			
			logger.debug(current.getName() + " received for calculating rank.");

			if (current.getStatus() == Status.PASS) {

				for (Student jStudent : calculatedRankList) {
					System.out.println(current.getName() + current.getRank() + " Ranking " + jStudent.getName()
							+ jStudent.getRank());
					if (current.getTotalMarks() < jStudent.getTotalMarks()) {
						current.setRank(current.getRank() + 1);
					} else {
						jStudent.setRank(jStudent.getRank() + 1);
					}
				}
				
				calculatedRankList.add(current);
				
				logger.debug(current.getName() + " rank calculated after comparing with " + calculatedRankList);
			}
		}

		logger.debug("Ranking students completed.");
	}
	
	

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public void truncateTables() {
		// TODO Auto-generated method stub
		getStudentDAO().deleteAllData();
	}

}
