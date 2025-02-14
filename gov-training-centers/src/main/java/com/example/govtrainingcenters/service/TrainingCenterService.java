package com.example.govtrainingcenters.service;

import java.util.List;
import java.util.Optional;

import com.example.govtrainingcenters.dto.TrainingCenterDTO;

public interface TrainingCenterService {
	
	public TrainingCenterDTO createTrainingCenter (TrainingCenterDTO trainingDto);
	
	public List<TrainingCenterDTO> getAllTrainingCenters(Optional<String>state , Optional<String>city , Optional<String>course);
	
}
