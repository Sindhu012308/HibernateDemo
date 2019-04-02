package com.sindhu.jpa.hibernate.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.sindhu.jpa.hibernate.demo.DemoApplication;
import com.sindhu.jpa.hibernate.demo.entity.Course;
import com.sindhu.jpa.hibernate.demo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(CourseRepository.class);
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void JPQL_Basic() {
		//List resultList = entityManager.createQuery("Select c from Course c").getResultList();
		List resultList = entityManager.createNamedQuery("get_all_courses").getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void JPQL_typed() {
		//TypedQuery<Course> createQuery = entityManager.createQuery("Select c from Course c", Course.class);
		TypedQuery<Course> createQuery = entityManager.createNamedQuery("get_all_courses", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void JPQL_Where() {
		TypedQuery<Course> createQuery = entityManager.createQuery("Select c from Course c where name like '%Master Course'", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Select c from Course c where name like '%Master Course-> {}", resultList);
	}
	
	@Test
	public void JPQL_courses_withoutStudents() {
		TypedQuery<Course> createQuery = entityManager.createQuery("Select c from Course c where c.student is empty", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Results of Courses without Students-> {}", resultList);
	}

	@Test
	public void JPQL_courses_with_atleast_1_Students() {
		TypedQuery<Course> createQuery = entityManager.createQuery("Select c from Course c where size(c.student) >= 2", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Results of courses with atleast two students-> {}", resultList);
	}
	
	@Test
	public void JPQL_courses_orderby_Students() {
		TypedQuery<Course> createQuery = entityManager.createQuery("Select c from Course c order by size(c.student)", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Results of in the order of size of students-> {}", resultList);
	}
	
	@Test
	public void JPQL_Students_with_passport_inCertainPattern() {
		TypedQuery<Student> createQuery = entityManager.createQuery("Select s from Student s where s.passport.number like '%1234%' ", Student.class);
		List<Student> resultList = createQuery.getResultList();
		logger.info("Results of Students whose passport number contains 1234 in it -> {}", resultList);
	}
	
	//JOIN -> Select c,s from Course c JOIN c.student s
	//LEFT JOIN -> Select c,s from Course c LEFT JOIN c.student s
	//CROSS JOIN -> Select c,s from Course c, s.student 
	
	@Test
	public void JPQL_Join_CourseAndStudent() {
		Query query = entityManager.createQuery("Select c,s from Course c JOIN c.student s");
		List<Object[]> resultList = query.getResultList();
		//JPQL Keeps the result as an array with course as first element of the array and student as second element of array
		//which means it return Array Of Array
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result: resultList) {
			logger.info("Course{}, student{}", result[0],result[1]);
		}
	}
	
	@Test
	public void JPQL_LeftJoin_CourseAndStudent() {
		Query query = entityManager.createQuery("Select c,s from Course c LEFT JOIN c.student s");
		List<Object[]> resultList = query.getResultList();
		//JPQL Keeps the result as an array with course as first element of the array and student as second element of array
		//which means it return Array Of Array
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result: resultList) {
			logger.info(" Left join Course{}, student{}", result[0],result[1]);
		}
	}
	
	@Test
	public void JPQL_CrossJoin_CourseAndStudent() {
		Query query = entityManager.createQuery("Select c,s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		//JPQL Keeps the result as an array with course as first element of the array and student as second element of array
		//which means it return Array Of Array
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result: resultList) {
			logger.info(" Cross join Course{}, student{}", result[0],result[1]);
		}
	}
	
}
