package com.sindhu.jpa.hibernate.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sindhu.jpa.hibernate.demo.DemoApplication;
import com.sindhu.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class PerformanceTuningTest {
	
	private Logger logger=LoggerFactory.getLogger(PerformanceTuningTest.class); 
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	@Transactional
	public void solvingNPlusOneProb_EntityGraphSolution() {
		
		EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
		Subgraph<Object> subgraph = entityGraph.addSubgraph("student");
		
		@SuppressWarnings("unchecked")
		List<Course> courses=entityManager.createNativeQuery("get_all_courses", Course.class)
				.setHint("javax.persistance.loadgraph", entityGraph)
				.getResultList();
		
		for(Course course:courses) {
			logger.info("Course -> {} Student -> {}",course, course.getStudent());
		}
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProb_JoinFetch() {
		
		List<Course> courses=entityManager.createNativeQuery("get_all_courses_join_fetch", Course.class)
				.getResultList();
		
		for(Course course:courses) {
			logger.info("Course -> {} Student -> {}",course, course.getStudent());
		}
	}

}
