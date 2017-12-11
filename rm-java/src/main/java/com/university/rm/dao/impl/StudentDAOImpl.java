package com.university.rm.dao.impl;

import java.util.List;
import org.apache.log4j.Logger;
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
		Session session = this.sessionFactory.getCurrentSession();
		//Transaction tx = session.beginTransaction();
		logger.info("Transaction for saing student details started");
		/*for (Student student : students) {
			session.persist(student);
		}*/
		
		//tx.commit();
		//session.close();
		
	}
	
}
