package com.tax.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax.dto.EmployeeDto;
import com.tax.model.Employee;
import com.tax.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;


	public EmployeeDto save(EmployeeDto employeeDto) {
		Employee employeeModel = employeeRepository.save(new ModelMapper().map(employeeDto, Employee.class));
		EmployeeDto returnEmployeeDto = new ModelMapper().map(employeeModel, EmployeeDto.class);
		returnEmployeeDto.setPhoneNumber(
				Stream.of(employeeModel.getPhoneNumber().replaceAll("\\[", "").replaceAll("\\]", "").split(",", -1))
						.collect(Collectors.toList()));
		return returnEmployeeDto;
	}


	public EmployeeDto getEmployee(Long employeeID) {
		Employee employeeModel = employeeRepository.findByEmployeeID(employeeID);
		EmployeeDto employeeDto = new ModelMapper().map(employeeModel, EmployeeDto.class);
		return employeeDto;

	}


	public List<EmployeeDto> getAllEmployee() {
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeDto> employeesDto = new ArrayList<>();
		employees.stream().forEach(employee -> {
			EmployeeDto employeeDto = new ModelMapper().map(employee, new TypeToken<EmployeeDto>() {
			}.getType());
			employeesDto.add(employeeDto);
		});
		return employeesDto;
	}

}
