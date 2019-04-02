package com.sindhu.jpa.hibernate.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sindhu.jpa.hibernate.demo.entity.Course;
import com.sindhu.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.sindhu.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.sindhu.jpa.hibernate.demo.entity.Review;
import com.sindhu.jpa.hibernate.demo.entity.Student;
import com.sindhu.jpa.hibernate.demo.repository.CourseRepository;
import com.sindhu.jpa.hibernate.demo.repository.EmployeeRepository;
import com.sindhu.jpa.hibernate.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	private Logger logger = LoggerFactory.getLogger(DemoApplication.class);
		
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		employeeRepository.insert(new FullTimeEmployee("Jack",new BigDecimal(60000)));
		
		employeeRepository.insert(new PartTimeEmployee("Jill",new BigDecimal(80)));
		
		logger.info("All PartTimeEmployee -> {}", employeeRepository.retrieveAllPartTimeEmployees());
		logger.info("All FullTimeEmployee -> {}", employeeRepository.retrieveAllFullTimeEmployees());

		
		//studentRepository.insertStudentAndCourse(new Student("Jack"),new Course("MicroServices Course "));
		
		/*List<Review> reviews = new ArrayList<>();
		
		reviews.add(new Review("5","Great Hands-on Stuff"));
		reviews.add(new Review("4","Great Stuff"));
		courseRepository.addReviewsForCourse(10002l,reviews);
		*/
		
		//studentRepository.saveStudentWithPassport();
		
		//logger.info("Finding a course with ID 1: {}",repository.findById(10001l));
		
		//repository.deleteById(10001l);
		
		//repository.save(new Course("Hibernate Master Course"));
		
		//repository.playWithEntityManager();
	}

}
