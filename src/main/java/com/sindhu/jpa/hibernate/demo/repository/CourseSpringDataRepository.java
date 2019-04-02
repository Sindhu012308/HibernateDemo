package com.sindhu.jpa.hibernate.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.sindhu.jpa.hibernate.demo.entity.Course;

@RepositoryRestResource(path="/courses")
public interface CourseSpringDataRepository extends JpaRepository<Course,Long>{

	List<Course> findByNameAndId(String name, Long id);
	List<Course> findByName(String name);
	List<Course> countByName(String name);
	List<Course> findByNameOrderByIdDesc(String name);
	List<Course> deleteByName(String name);
	
	@Query("Select c from Course c where name like '%Master' ")
	List<Course> courseWithMasterInName();

	@Query(value = "Select * from Course c where name like '%Master'", nativeQuery=true)
	List<Course> courseWithMasterInNameUsingNativeQuery();
	
	@Query(name="get_all_Master_courses")
	List<Course> courseWithMasterInNameInnamedQuery();
}
