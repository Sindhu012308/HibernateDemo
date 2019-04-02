package com.sindhu.jpa.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String name;
	
	@Embedded
	private Address address;
	//Relation between Student and Passport is One-To-One
	//When we fetch for a student details, 'Eager' fetching takes place and Passport details like ID and Passport Number are also shown
	//But passport full details should not be shown, so we convert EAGER fetching to LAZY fetching Which doesnot show the passport details
	//LAZY Fetching means You get the details when only you needed That
	@OneToOne(fetch=FetchType.LAZY)
	private Passport passport;
	
	//ManyToMany Fetch Type is LAZY by default
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="STUDENT_COURSE",
		joinColumns = @JoinColumn(name= "STUDENT_ID"),
		inverseJoinColumns = @JoinColumn(name="Course_ID"))
	private List<Course> course = new ArrayList<>();
	
	protected Student() {
		
	}
	
	public Student(String name) {
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

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public List<Course> getCourse() {
		return course;
	}
	
	//I dont want to set the course but i want to add the course or remoce it
	public void addCourse(Course course) {
		this.course.add(course);
	}

	public void removeCourse(Course course) {
		this.course.remove(course);
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + "]";
	}
	
}
