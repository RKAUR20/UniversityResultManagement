package com.university.rm.service;

import java.util.List;

import com.university.rm.model.Student;

public interface ResultCalculatorService {
	
	public void calculateAndSaveStudentsResult(List<Student> students);

	public void truncateTables();

}
