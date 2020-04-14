package com.assignment.employee.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.employee.models.Employee;
import com.assignment.employee.models.EmployeeModel;
import com.assignment.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;

	public List<EmployeeModel> getAllEmployeesByDeptNo(String deptNo){
		return repository.getAllEmployeesByDeptNo(deptNo);
		
	}
	
	
	public List<Employee> getEmployeesFromHiredafterDateAndsalary(Integer vSalary, String date) {
		return repository.getEmployeesFromHiredafterDateAndsalary(vSalary, date);
	}
	
	
	public void deleteEmployeeOnHireDate(String date) {
		repository.deleteEmployeeOnHireDate(date);
	}
	
}
