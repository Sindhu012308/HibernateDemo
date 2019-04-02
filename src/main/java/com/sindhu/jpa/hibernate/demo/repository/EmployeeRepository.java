package com.sindhu.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sindhu.jpa.hibernate.demo.entity.Course;
import com.sindhu.jpa.hibernate.demo.entity.Employee;
import com.sindhu.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.sindhu.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.sindhu.jpa.hibernate.demo.entity.Review;

@Repository
@Transactional
public class EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);
	
	@Autowired 
	EntityManager entityManager;

	//insert or update
	public void insert(Employee employee) {
			 entityManager.persist(employee);
	}
	
	public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
		return entityManager.createQuery("Select e from PartTimeEmployee e",PartTimeEmployee.class).getResultList();
	}
	
	public List<FullTimeEmployee> retrieveAllFullTimeEmployees(){
		return entityManager.createQuery("Select e from FullTimeEmployee e",FullTimeEmployee.class).getResultList();
	}
}

/*
	Select * from Table, is Sql query which fetches data from tables.
	Select c from Course c, is JPQL Query which fetches data from the entity. Jpql queries are written on entities. 
	JPQL -> Java Persistance Query Language.
	this Jpql query on entities are converted to sql query on tables by hibernate.
*/