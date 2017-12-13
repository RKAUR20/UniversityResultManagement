package com.university.rm.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
	public void addStudents(List<Student> students) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		logger.info("Transaction for saving student details started");
		students.forEach(student -> session.persist(student));
		tx.commit();
		logger.info("Transaction for saving student details ended :: commit completed");
		session.close();
	}
	
	@Override
	public void deleteAllData() {
		
		String SUBJECT_QUERY = "delete Subject";
		String STUDENT_QUERY = "delete Student";
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		logger.info("Transaction for deleting student details started.");
		Query subjectQuery = session.createQuery(SUBJECT_QUERY);
		Query studentQuery = session.createQuery(STUDENT_QUERY);
		subjectQuery.executeUpdate();
		studentQuery.executeUpdate();
		tx.commit();
		logger.info("Transaction for deleting student details completed.");
		session.close();
		
	}
	
	
}
