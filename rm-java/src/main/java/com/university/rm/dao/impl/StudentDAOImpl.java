package com.university.rm.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.university.rm.dao.StudentDAO;
import com.university.rm.model.Student;

@Repository
@Transactional
public class StudentDAOImpl implements StudentDAO {

	private static final Logger logger = Logger.getLogger(StudentDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addStudents(Student s) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(s);
		logger.info("Student saved successfully, Student Details="+s);
	}
	
}
