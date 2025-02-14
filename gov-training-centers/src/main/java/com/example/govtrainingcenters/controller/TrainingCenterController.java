package com.example.govtrainingcenters.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.govtrainingcenters.dto.TrainingCenterDTO;
import com.example.govtrainingcenters.request.pojo.TrainingCenterRequest;
import com.example.govtrainingcenters.response.pojo.ApiResponse;
import com.example.govtrainingcenters.response.pojo.TrainingCenterResponse;
import com.example.govtrainingcenters.service.TrainingCenterService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping(
		value = "/api/v1/training-centers",
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
@Slf4j
public class TrainingCenterController {
	
	private TrainingCenterService trainingCenterService;
	
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<ApiResponse<TrainingCenterResponse>> createTrainingCenter(@Valid @RequestBody TrainingCenterRequest request) {
		
		log.info("My TrainingCenter REquest Object:" + request);
		
		TrainingCenterDTO trainingCenterDTO = modelMapper.map(request, TrainingCenterDTO.class);
		
		log.info("My Converted DTO Object is : " + trainingCenterDTO);
		
		TrainingCenterDTO saVedTrainingCenterDTO =trainingCenterService.createTrainingCenter(trainingCenterDTO);
		
		TrainingCenterResponse trainingCenterResponse = modelMapper.map(saVedTrainingCenterDTO, TrainingCenterResponse.class);
		
		ApiResponse<TrainingCenterResponse>apiResponse = new ApiResponse<>(
				"Training Center created successfully",
				HttpStatus.CREATED.value(),
				trainingCenterResponse
				);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
		
		
	
	}
	
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<TrainingCenterResponse>>> getAllTrainingCenters(
			@RequestParam(required = false) Optional<String> state,
			@RequestParam(required = false) Optional<String> city,
			@RequestParam(required = false) Optional<String> course){
		
		List<TrainingCenterDTO> listOfTrainingCenterDTOs = trainingCenterService.getAllTrainingCenters(state , city , course);
		List<TrainingCenterResponse> trainingCenterResponses = listOfTrainingCenterDTOs
				.stream()
				.map(dto->modelMapper.map(dto, TrainingCenterResponse.class))
				.collect(Collectors.toList());
		
		ApiResponse<List<TrainingCenterResponse>>apiResponse = new ApiResponse<>(
				"Training Centers retrieved successfully",
				HttpStatus.OK.value(),
				trainingCenterResponses
				);
		
		return ResponseEntity.ok(apiResponse);
	}
}
