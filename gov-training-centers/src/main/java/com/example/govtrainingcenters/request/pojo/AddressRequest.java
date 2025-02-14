package com.example.govtrainingcenters.request.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private String detailedAddress;
    private String city;
    private String state;
    private String pincode;
}
