package com.sindhu.jpa.hibernate.demo.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class PartTimeEmployee extends Employee{

	private BigDecimal hourlyWage;
	
	protected PartTimeEmployee() {
		
	}
	
	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage=hourlyWage;
	}
	
}
