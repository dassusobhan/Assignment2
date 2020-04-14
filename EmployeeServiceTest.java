package com.assignment.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import com.assignment.employee.models.Employee;
import com.assignment.employee.models.EmployeeModel;
import com.assignment.employee.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService service;

	@Mock
	EmployeeRepository repository;

	//@Test
	public void getAllEmployeesByDeptNoTest() { // return

		List<EmployeeModel> emp = new ArrayList<>();

		String deptNo = "";

		when(repository.getAllEmployeesByDeptNo(deptNo)).thenReturn(emp);

		service.getAllEmployeesByDeptNo(deptNo);

		verify(repository).getAllEmployeesByDeptNo(deptNo);

	}

	//@Test
	public void getEmployeesFromHiredafterDateAndsalaryTest() { // return
		
		Integer vSalary = 50000;
		String date = "1986-12-09";
		//String date = "";

		service.getEmployeesFromHiredafterDateAndsalary(vSalary, date);

		verify(repository).getEmployeesFromHiredafterDateAndsalary(vSalary, date);

	}

	//@Test
	public void deleteEmployeeOnHireDateTest() {
		String date = "1986-12-09";
		//String date = "1986-12-09gjgjg";

		service.deleteEmployeeOnHireDate(date);

		verify(repository).deleteEmployeeOnHireDate(date);
	}

}
