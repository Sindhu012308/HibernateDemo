package com.sindhu.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//MappedSuperclass is a class which is used by partTimeEmployee and fullTimeEmployee
//@Entity
//@Table(name="Employee")
//Inheritance is used to inherit both partTiMEemployee and fullTimeEmployee in employee table and make it single table
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="Employee_type")
public abstract class Employee {

	@Id
	@GeneratedValue
	private  Long id;
	
	// name is not Null 
	@Column(name="name", nullable = false)
	private String name;
	
	protected Employee() {
		
	}
	
	public Employee(String name) {
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

	@Override
	public String toString() {
		return "\n Employee [id=" + id + ", name=" + name + "]";
	}
	
}

