package com.tax.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax.dto.EmployeeDto;
import com.tax.dto.TaxDto;

@Service
public class TaxService  {

	@Autowired
	EmployeeService employeeService;
	
	
	public List<TaxDto> calculateTax() {
		
		List<EmployeeDto> employees = employeeService.getAllEmployee();
		List<TaxDto> employeesTax = new ArrayList<TaxDto>();
		for(EmployeeDto employee : employees) {
			TaxDto employeeTaxDto = getTax(employee);
			employeesTax.add(employeeTaxDto);
		}
		return employeesTax;
		
	}
	
	public TaxDto getTax(EmployeeDto employeeDto) {
		TaxDto employeeTaxDto = new TaxDto();
		employeeTaxDto.setId(employeeDto.getId());
		employeeTaxDto.setFirstName(employeeDto.getFirstName());
		employeeTaxDto.setJoiningDate(employeeDto.getJoiningDate());
		employeeTaxDto.setLastName(employeeDto.getLastName());
		employeeTaxDto.setSalaryYearly(TaxCalculate.getTotalSalary(employeeDto.getJoiningDate(), employeeDto.getSalary(), 0));
		employeeTaxDto.setTaxAmount(TaxCalculate.getTax(employeeTaxDto.getSalaryYearly()));
		employeeTaxDto.setCessAmount(TaxCalculate.getCess(employeeTaxDto.getSalaryYearly()));
		return employeeTaxDto;
	}

}
