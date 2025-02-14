package com.example.govtrainingcenters.request.pojo;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCenterRequest {
	
	 @NotBlank(message = "Center Name is required")
	    @Size(max = 40, message = "Center Name must be less than 40 characters")
	    private String centerName;

	    @NotBlank(message = "Center Code is required")
	    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center Code must be 12 characters")
	    private String centerCode;

	    @Valid
	    @NotNull(message = "Address is required")
	    private AddressRequest address;

	    
	    private Integer studentCapacity;


	    private List<String> coursesOffered;
	    
	    @Email(message = "Invalid email format")
	    private String contactEmail;

	    @Pattern(regexp = "^(?!([0-9])\\1{9})[0-9]{10}$", message = "Invalid phone number")
	    private String contactPhone;
}
