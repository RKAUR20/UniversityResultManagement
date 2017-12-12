package com.university.rm.dao;

import java.util.List;

import com.university.rm.model.Student;

public interface StudentDAO {
	
	public void addStudents(List<Student> students);

	public void deleteAllData();

}
