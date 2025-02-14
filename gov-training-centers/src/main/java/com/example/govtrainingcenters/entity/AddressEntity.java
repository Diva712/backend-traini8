package com.example.govtrainingcenters.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
	
	private String detailedAddress;
    private String city;
    private String state;
    private String pincode;

}
