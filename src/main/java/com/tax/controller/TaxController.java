package com.tax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tax.dto.EmployeeDto;
import com.tax.dto.TaxDto;
import com.tax.service.EmployeeService;
import com.tax.service.TaxService;

@RestController
@RequestMapping("/employeeApi")
public class TaxController {

	@Autowired
	TaxService taxService;
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping(value = "/addEmployee", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		try {
			return new ResponseEntity<EmployeeDto>(employeeService.save(employeeDto), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/calculateTax", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaxDto>> getEmployeesTax() {
		try {
			List<TaxDto> tax = taxService.calculateTax();
			return new ResponseEntity<List<TaxDto>>(tax, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
