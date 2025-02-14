package com.example.govtrainingcenters.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.govtrainingcenters.entity.TrainingCenterEntity;


@Repository
public interface TrainingCenterDao extends JpaRepository<TrainingCenterEntity , Long>{
	
	
	//Fetching all the records for state
	List<TrainingCenterEntity> findByAddress_State(String state);
	
	//Fetching all the records for city
	List<TrainingCenterEntity>findByAddress_City(String city);
		
	//Fetching all the records for course
	@Query("SELECT tc FROM TrainingCenterEntity tc JOIN tc.coursesOffered c WHERE c = :course")
	List<TrainingCenterEntity>findByCoursesOffered(@Param("course") String course);
	
	//Fetching all the records for state and course
	@Query("SELECT DISTINCT tc FROM TrainingCenterEntity tc JOIN tc.coursesOffered c WHERE tc.address.state = :state AND c = :course")
	List<TrainingCenterEntity> findByStateAndCourse(@Param("state") String state, @Param("course") String course);
	
	
	//Fetching all the records for the city and course
	@Query("SELECT DISTINCT tc FROM TrainingCenterEntity tc JOIN tc.coursesOffered c WHERE tc.address.city = :city AND c = :course")
	List<TrainingCenterEntity> findByCityAndCourse(@Param("city") String state, @Param("course") String course);
	
	//Fetching all the records for the state , city , course
	@Query("SELECT DISTINCT tc FROM TrainingCenterEntity tc JOIN tc.coursesOffered c WHERE tc.address.state = :state AND tc.address.city = :city AND c = :course")
	List<TrainingCenterEntity> findByStateCityAndCourse(@Param("state") String state, @Param("city") String city, @Param("course") String course);

	   

	
} 
