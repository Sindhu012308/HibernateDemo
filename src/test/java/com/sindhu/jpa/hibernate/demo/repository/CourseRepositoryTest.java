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
import com.sindhu.jpa.hibernate.demo.entity.Course;
import com.sindhu.jpa.hibernate.demo.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(CourseRepository.class);
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	CourseRepository repository;
	
	@Test
	public void testfindById() {
		Course course = repository.findById(10001L);
		assertEquals("Spring Master Course", course.getName());
	}
	
	@Test
	@Transactional
	public void testfindById_FirstLevelCache() {
		Course course = repository.findById(10001L);
		logger.info("First Course Retrieved {}", course );
		Course course1 = repository.findById(10001L);
		logger.info("First Course Retrieved again {}", course1 );

		assertEquals("Spring Master Course", course.getName());
		assertEquals("Spring Master Course", course1.getName());
	}
	
	@Test
	@DirtiesContext
	//DirtiesContext annotation is used to overcome the following conditions
	//Unit test cases run in random manner. suppose delete test case run first and then findById runs
	//In that case If 10002L was deleted and we are unknowingly we search for 10002L using FindbyId testcase then findById case will fail
	// to overcome these we use @DirtiesContext Annotation
	//So 10002L should not be deleted actually in the database so we use DirtiesContext Annotation 
	//The data is rolled back to original data in database by using @DirtiesContext
	public void testDeleteById() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}

	@Test
	@DirtiesContext
	public void testSave() {
		//Get a Course
		Course course = repository.findById(10003l);
		assertEquals("Spring MVC Master Course", course.getName());
		// Update Details
		course.setName("Spring MVC Master Course-Updated");
		repository.save(course);
		//Check the value
		assertEquals("Spring MVC Master Course-Updated", course.getName());
		
	}
	
	@Test
	@DirtiesContext
	public void testPlayWithEntityManager() {
	 repository.playWithEntityManager();
	}
	
	@Test
	//@DirtiesContext
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course = repository.findById(10003l);
		logger.info("{}",course.getReviews());
	}
	
	@Test
	//@DirtiesContext
	@Transactional
	public void retrieveCourseForReview() {
		Review review = entityManager.find(Review.class,40002l);
		logger.info("{}",review.getCourse());
	}
}
