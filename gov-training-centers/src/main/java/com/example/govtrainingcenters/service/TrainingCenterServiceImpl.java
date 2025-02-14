package com.example.govtrainingcenters.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.govtrainingcenters.dao.TrainingCenterDao;
import com.example.govtrainingcenters.dto.TrainingCenterDTO;
import com.example.govtrainingcenters.entity.TrainingCenterEntity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@AllArgsConstructor
public class TrainingCenterServiceImpl implements TrainingCenterService {
	
	private TrainingCenterDao trainingCenterDao;
	
	private ModelMapper modelMapper;
	
	
	@Override
	public TrainingCenterDTO createTrainingCenter(TrainingCenterDTO trainingDto) {
		
		TrainingCenterEntity entity = modelMapper.map(trainingDto, TrainingCenterEntity.class);
		entity.setCreatedOn(System.currentTimeMillis());
		
		log.info("My converted entity Object : " + entity);
		
		TrainingCenterEntity savedTrainingCenter = trainingCenterDao.save(entity);
		
		log.info("My saved entity Object :" + savedTrainingCenter );
		
		return modelMapper.map(savedTrainingCenter, TrainingCenterDTO.class);
		
		
		
	}


	@Override
	public List<TrainingCenterDTO> getAllTrainingCenters(Optional<String>state , Optional<String>city , Optional<String>course) {
		log.info("Fetching training centers with filters if present - State : {} , city: {}, Course: {} " , state , city , course);
		List<TrainingCenterEntity> entities ;
		
		if(state.isPresent() && city.isPresent() && course.isPresent()) {
			entities = trainingCenterDao.findByStateCityAndCourse(state.get(), city.get(), course.get());
		}
		else if(state.isPresent() && course.isPresent()) {
			entities = trainingCenterDao.findByStateAndCourse(state.get(), course.get());
		}
		else if(city.isPresent() && course.isPresent()) {
			entities = trainingCenterDao.findByCityAndCourse(city.get(),course.get());
		}
		else if(state.isPresent()) {
			entities = trainingCenterDao.findByAddress_State(state.get());
		}
		else if(city.isPresent()) {
			entities = trainingCenterDao.findByAddress_City(city.get());
		}
		else if(course.isPresent()) {
			entities = trainingCenterDao.findByCoursesOffered(course.get());
		}
		else {
			entities = trainingCenterDao.findAll();
		}
		
		if(entities.isEmpty()) {
			log.warn("No Training centers found yet !!");
		}
		
		
		return entities.stream()
					.map(entity->modelMapper.map(entity, TrainingCenterDTO.class))
					.collect(Collectors.toList());
	}

}
