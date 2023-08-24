package com.tax.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeDto {

	private Long id;

	private String firstName;
	
	private String email;

	private String lastName;

	private Date joiningDate;

	private Long salary;
	
	private List<String> phoneNumber;
	
}
