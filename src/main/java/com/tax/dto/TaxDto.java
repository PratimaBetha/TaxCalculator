package com.tax.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TaxDto {

	private Long id;

	private String firstName;

	private String lastName;

	private Date joiningDate;

	private Long salaryYearly;
	
	private Double taxAmount;
	
	private Double cessAmount;

}
