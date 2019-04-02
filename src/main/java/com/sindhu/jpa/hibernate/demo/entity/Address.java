package com.sindhu.jpa.hibernate.demo.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String line1;
	private String line2;
	private String line3;
	
	protected Address() {
		
	}
	
	public Address(String line1, String line2, String line3) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getLine3() {
		return line3;
	}

	public void setLine3(String line3) {
		this.line3 = line3;
	}
	
}
