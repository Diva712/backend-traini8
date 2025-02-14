package com.example.govtrainingcenters.response.pojo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
	
	private int statusCode;
	private String message;
	private String path;
	private LocalDateTime timestamp;

}
