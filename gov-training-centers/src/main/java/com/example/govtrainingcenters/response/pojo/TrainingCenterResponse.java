package com.example.govtrainingcenters.response.pojo;

import java.util.List;

import com.example.govtrainingcenters.dto.AddressDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCenterResponse {
	 private Long id;
	    private String centerName;
	    private String centerCode;
	    private AddressResponse address;
	    private Integer studentCapacity;
	    private List<String> coursesOffered;
	    private String contactEmail;
	    private String contactPhone;
	    private Long createdOn;
   
}
