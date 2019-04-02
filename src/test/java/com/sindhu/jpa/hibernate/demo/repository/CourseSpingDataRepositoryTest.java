package com.sindhu.jpa.hibernate.demo.repository;

import static org.junit.Assert.*;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sindhu.jpa.hibernate.demo.DemoApplication;
import com.sindhu.jpa.hibernate.demo.entity.Course;
import com.sindhu.jpa.hibernate.demo.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseSpingDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(CourseRepository.class);
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	CourseSpringDataRepository repository;
	
	@Test
	public void testfindById() {
		Optional<Course> course = repository.findById(10001L);
		assertTrue(course.isPresent());
	}
	
	@Test
	public void testfindById_empty() {
		Optional<Course> course = repository.findById(100001L);
		assertFalse(course.isPresent());
	}
	
	@Test
	public void testfindAll_Sort() {
		Sort sort = new Sort(Sort.Direction.DESC,"name");
		logger.info("Sorted Courses-> {}", repository.findAll(sort));
		logger.info("Count -> {}", repository.count());
	}
	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page -> {}", firstPage.getContent());
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {}", secondPage.getContent());
	}
}
