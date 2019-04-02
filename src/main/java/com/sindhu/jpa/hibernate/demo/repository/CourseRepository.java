package com.sindhu.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sindhu.jpa.hibernate.demo.entity.Course;
import com.sindhu.jpa.hibernate.demo.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(CourseRepository.class);
	
	@Autowired 
	EntityManager entityManager;
	
	public Course findById(Long id) {
		return entityManager.find(Course.class,id);
	}
	
	//insert or update
	public Course save(Course course) {
		if(course.getId()==null) {
			
			//insert
			 entityManager.persist(course);
		}
		else {
			//update
			 entityManager.merge(course);
		}
		return course;
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		entityManager.remove(course);
	}
	
	public void playWithEntityManager(){
		Course course1 = new Course("Web Services in detail");
		entityManager.persist(course1);
		entityManager.flush();
		//The flush method is used to send the changes on objects that entityManager is tracing to the database till then.
		Course course2 = new Course("Angular JS in detail");
		entityManager.persist(course2);
		entityManager.flush();
		//send the changes of Course2 to database -- flush() after course2
		//Even though we are not saving the updated name, entity manager keeps track of the course we created 
		//here and saves automatically the changes to it even if we didnt save it. This is due to the annotation @Transactional.
		course1.setName("Web Services in detail-Updated");
		entityManager.flush();
		
		//No the changes on course2 is not saved to database automatically without save method due to 
		//entityManager is detached from Course2 due to detach(course2) but it saves course1 changes as usual as there is no detach on course1.
		//We can use entityManager.clear() instead of entityManager.detach(course2) 
		//but clear() clears all the data on entityManager both course1 and course2 changes after that method 
		
		entityManager.detach(course2);
		entityManager.refresh(course1);
		//Refresh is used to remove the recent changes and save it as declared one.
		course2.setName("Angular JS in detail-Updated");
		entityManager.flush();
		
		Course course3 = findById(10002l);
		course3.setName("Spring MVC Master Course-Updated");
	}
	
	public void addHardReviewsForCourse() {
		//get the course 10003
		Course course = findById(10003l);
		logger.info("course.getReviews -> {}",course.getReviews());
		
		//add 2 review to it
		Review review1 = new Review("5","Great Hands-on Stuff");
		Review review2 = new Review("4", "Good Stuff");
		
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		//save it to the database
		entityManager.persist(review1);
		entityManager.persist(review2);
	}
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		//get the course 
		Course course = findById(courseId);
		logger.info("course.getReviews -> {}",course.getReviews());
		
		//adding reviews to course
		for(Review review: reviews) {
			course.addReview(review);
			review.setCourse(course);
			entityManager.persist(review);
		}
		
	}
}

/*
	Select * from Table, is Sql query which fetches data from tables.
	Select c from Course c, is JPQL Query which fetches data from the entity. Jpql queries are written on entities. 
	JPQL -> Java Persistance Query Language.
	this Jpql query on entities are converted to sql query on tables by hibernate.
*/