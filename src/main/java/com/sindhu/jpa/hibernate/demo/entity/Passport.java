package com.sindhu.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String number;
	
	//mapped by should be added on the non-owning side of relationship
	//which means you should see passport of student in student table. so, student is owning side 
	//we should not see student in passport table, then passport is non-owning side
	@OneToOne(fetch=FetchType.LAZY, mappedBy="passport")
	private Student student;
	
	protected Passport() {
		
	}
	
	public Passport(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Passport [number=" + number + "]";
	}

	
}
