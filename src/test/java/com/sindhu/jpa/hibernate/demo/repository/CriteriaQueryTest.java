package com.sindhu.jpa.hibernate.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
public class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(CourseRepository.class);
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void CriteriaQuery_Basic() {
		//"Select c from Course c"
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define Predicates etc using Criteria Builder.
		
		//4. Add predicates etc to the Criteria Query
		
		//5.Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {}", resultList);
	}
		
	@Test
	public void allCoursesHavingMaster() {
		//"Select c from Course c where name like '%Master%' "
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define Predicates etc using Criteria Builder.
		Predicate likeMaster = cb.like(courseRoot.get("name"), "%Master%");
		
		//4. Add predicates etc to the Criteria Query
		cq.where(likeMaster);
		
		//5.Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query where name contains Master in it -> {}", resultList);
	}
	
	@Test
	public void allCoursesWithOutStudents() {
		//"Select c from Course c where c.student is empty "
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define Predicates etc using Criteria Builder.
		Predicate noStudents = cb.isEmpty(courseRoot.get("student"));
		
		//4. Add predicates etc to the Criteria Query
		cq.where(noStudents);
		
		//5.Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query which course doesnot have any students  -> {}", resultList);
	}
	
	@Test
	public void join() {
		//"Select c from Course c join c.student s "
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define Predicates etc using Criteria Builder.
		Join<Object, Object> join = courseRoot.join("student");
		
		//4. Add predicates etc to the Criteria Query

		
		//5.Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Join Course And Student -> {}", resultList);
	}
	
	@Test
	public void left_join() {
		//"Select c from Course c join c.student s "
		//1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define Predicates etc using Criteria Builder.
		Join<Object, Object> join = courseRoot.join("student", JoinType.LEFT);
		
		//4. Add predicates etc to the Criteria Query

		
		//5.Build the typedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info(" Left Join Course And Student -> {}", resultList);
	}
}
