package com.sindhu.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sindhu.jpa.hibernate.demo.repository.CourseRepository;

@Entity
@Table(name="Course")
//@NamedQuery(name="get_all_courses", query="Select c from Course c")
//You can create only one NamedQuery for one class. Multiple NamedQuery are not allowed
//To create Multiple Named queries you can use @NamedQueries Annotation
@NamedQueries(value={ 
 				@NamedQuery(name="get_all_courses", query="Select c from Course c"),
 				@NamedQuery(name="get_all_courses_join_fetch", query="Select c from Course c JOIN FETCH c.student s"), 
 				@NamedQuery(name="get_all_Master_courses", query="Select c from Course c where name like '%Master Course'") })
 @Cacheable
@SQLDelete(sql="update course set is_deleted=true where id=?")
@Where(clause="is_deleted=false")
 public class Course {
         
	private static Logger logger = LoggerFactory.getLogger(Course.class);
	
         
    @Id
	@GeneratedValue
	private  Long id;
	
	// name is not Null 
	@Column(name="name", nullable = false)
	private String name;
	
	//if you want to store the updated or created time of the row 
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	private boolean isDeleted;
	
	@PreRemove
	private void preRemove() {
		logger.info("Setting isDeleted to true before deleting");
		this.isDeleted=true;
	}
	
	//One course has many review and one review is for one course => One-To-Many relationship from Couse to Review
	//Review table will have Course_id so we should keep mappedBy in non-owning side which is in course 
	//On the One-To-Many relationship Side fetching is lazy by default
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<>();
	
	//Owned By Student Table
	@ManyToMany(mappedBy="course", fetch=FetchType.EAGER)
	private List<Student> student = new ArrayList<>();
	
	public Course() {
		
	}
	
	public Course(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<Review> getReviews() {
		return reviews;
	}
	
	// I dont want others to set Reviews but i want others to add or remove review
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public List<Student> getStudent() {
		return student;
	}

	//I dont want to set the student, but instead i want to add or remove the student
	public void addStudent(Student student) {
		this.student.add(student);
	}
	
	public void removeStudent(Student student) {
		this.student.remove(student);
	}

	@Override
	public String toString() {
		return "\n Course [id=" + id + ", name=" + name + "]";
	}
	
}
