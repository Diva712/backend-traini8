package com.example.govtrainingcenters.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCenterDTO {
	 private Long id;
	    private String centerName;
	    private String centerCode;
	    private AddressDTO address;
	    private Integer studentCapacity;
	    private List<String> coursesOffered;
	    private String contactEmail;
	    private String contactPhone;
	    private Long createdOn;
}
