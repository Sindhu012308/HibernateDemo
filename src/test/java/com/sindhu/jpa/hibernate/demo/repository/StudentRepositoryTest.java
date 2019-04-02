package com.sindhu.jpa.hibernate.demo.repository;

import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.sindhu.jpa.hibernate.demo.DemoApplication;
import com.sindhu.jpa.hibernate.demo.entity.Address;
import com.sindhu.jpa.hibernate.demo.entity.Course;
import com.sindhu.jpa.hibernate.demo.entity.Passport;
import com.sindhu.jpa.hibernate.demo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(CourseRepository.class);
	
	@Autowired
	StudentRepository repository;
	
	@Autowired 
	EntityManager entityManager;
	
	@Test
	@Transactional
	public void testRetrieveStudenAndPassport() {
		Student student = entityManager.find(Student.class, 20000l);
		logger.info("Student with ID: 20000 -> {}",student);
		logger.info("Passport details of Student with ID:20000 -> {}",student.getPassport());
	}
	
	@Test
	@Transactional
	public void setAddressDetailsOfStudent() {
		Student student = entityManager.find(Student.class, 20000l);
		student.setAddress(new Address("No:10","Patamata","Vijayawada"));
		entityManager.flush();
		logger.info("Student with ID: 20000 -> {}",student);
		logger.info("Passport details of Student with ID:20000 -> {}",student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent(){
		Passport passport = entityManager.find(Passport.class, 30000l);
		logger.info("Passport details with Id: 30000l are -> {}",passport);
		logger.info("Student details with Passport Id: 30000l -> {}",passport.getStudent());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourses(){
		Student student = entityManager.find(Student.class, 20001l);
		logger.info("Student -> {}",student);
		logger.info("Courses -> {}",student.getCourse());
	}
	
	@Test
	@Transactional
	public void retrieveCourseAndStudents(){
		Course course = entityManager.find(Course.class, 10001l);
		logger.info("Course -> {}",course);
		logger.info("Students -> {}",course.getStudent());
	}
	
	
	@Test
	@Transactional
	// when you annotate with @Transactional, You are indirectly creating Persistance Context
	public void someTest() {
		//Database Operation 1 - Retrieve Student 
		Student student = entityManager.find(Student.class, 20000l);
		//Persistance Context(student)
		
		// Database Operation 2 - Retrieve Passport
		Passport passport = student.getPassport();
		//persistance Context(student, passport)
		
		//Database Operation 3 - Update Passport
		passport.setNumber("S123083");
		//persistance Context(student, passport++)

		//Database Operation 4 -  Update Student
		student.setName("John-Updated");
		//persistance Context(student++, passport)
		
		
		/*
		 Consider,
		 		Student student = entityManager.find(Student.class, 20000l);
		 				Passport passport = student.getPassport();
		 				
		 	If there is no @Transactional, persistance Context opens for first operation and ends soon after executing the operation.
		 	Similarly, it opens and closes for each operation which leads to exception in some cases.
		 	
		 	If there is @Transactional, Persistance Context opens at start of that method and ends when the method ends, Which successfully execute the method. 
		 */
	}
	
	
}
