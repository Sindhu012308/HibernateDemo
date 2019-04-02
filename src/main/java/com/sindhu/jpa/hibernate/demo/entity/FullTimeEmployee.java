package com.sindhu.jpa.hibernate.demo.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class FullTimeEmployee extends Employee{

	private BigDecimal salary;
	
	protected FullTimeEmployee() {
		
	}
	
	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary=salary;
	}
	
}
