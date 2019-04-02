package com.sindhu.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sindhu.jpa.hibernate.demo.entity.Course;
import com.sindhu.jpa.hibernate.demo.entity.Passport;
import com.sindhu.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(StudentRepository.class);
	
	@Autowired 
	EntityManager entityManager;
	
	public Student findById(Long id) {
		return entityManager.find(Student.class,id);
	}
	
	//insert or update
	public Student save(Student student) {
		if(student.getId()==null) {
			
			//insert
			 entityManager.persist(student);
		}
		else {
			//update
			 entityManager.merge(student);
		}
		return student;
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		entityManager.remove(student);
	}
	
	public void saveStudentWithPassport(){
		Passport passport = new Passport("z134563");
		entityManager.persist(passport);
		
		Student student = new Student("Mike");
		student.setPassport(passport);
		entityManager.persist(student);
	}
	
	public void insertHardCodedStudentAndCourse() {
		Student student = new Student("Jack");
		Course course = new Course("MicroServices Course ");
		entityManager.persist(course);
		entityManager.persist(student);
		
		student.addCourse(course);
		course.addStudent(student);
		
		entityManager.persist(student);
	}
	
	public void insertStudentAndCourse(Student student, Course course) {
		
		entityManager.persist(course);
		entityManager.persist(student);
		
		student.addCourse(course);
		course.addStudent(student);
		
		entityManager.persist(student);
	}
}

/*
	Select * from Table, is Sql query which fetches data from tables.
	Select c from Student c, is JPQL Query which fetches data from the entity. Jpql queries are written on entities. 
	JPQL -> Java Persistance Query Language.
	this Jpql query on entities are converted to sql query on tables by hibernate.
*/