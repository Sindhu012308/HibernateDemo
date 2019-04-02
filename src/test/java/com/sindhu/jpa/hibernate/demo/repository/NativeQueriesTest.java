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
import org.springframework.transaction.annotation.Transactional;

import com.sindhu.jpa.hibernate.demo.DemoApplication;
import com.sindhu.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(CourseRepository.class);
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void native_queries_basic() {
		Query query = entityManager.createNativeQuery("Select * from Course", Course.class);
		List resultList = query.getResultList();
		logger.info("Select * from Course -> {}", resultList);
	}
	
	@Test
	public void native_queries_where() {
		Query query = entityManager.createNativeQuery("Select * from Course where id = ? ", Course.class);
		query.setParameter(1, 10001l);
		List resultList = query.getResultList();
		logger.info("Select * from Course where id = ?  -> {}", resultList);
	}
	
	@Test
	public void native_queries_with_namedParameter() {
		Query query = entityManager.createNativeQuery("Select * from Course where id = :idValue ", Course.class);
		query.setParameter("idValue", 10001l);
		List resultList = query.getResultList();
		logger.info("Select * from Course where id = :idValue -> {}", resultList);
	}
	
	@Test
	@Transactional
	public void native_queries_Update() {
		Query query = entityManager.createNativeQuery("Update Course set last_updated_date=sysdate() ", Course.class);
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("no Of Rows Updated-> {}", noOfRowsUpdated);
	}
	
	
}

//Quries are mainly used on Massive updates means for large number of rows at a time
