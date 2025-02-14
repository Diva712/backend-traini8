package com.example.govtrainingcenters.response.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
	private String detailedAddress;
    private String city;
    private String state;
    private String pincode;
}
