package com.assignment.employee.controller;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.assignment.employee.models.Employee;
import com.assignment.employee.models.EmployeeModel;
import com.assignment.employee.service.EmployeeService;

@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController controller;


	MockMvc mockMvc;

	@Mock
	EmployeeService service;

	static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getAllEmployeesByDeptNoTest() throws Exception {
		List<EmployeeModel> employees = new ArrayList<>();
		EmployeeModel emp1 = new EmployeeModel(10009, FORMAT.parse("19/04/1952"), "Sumant Peac");
		EmployeeModel emp2 = new EmployeeModel(10010, FORMAT.parse("01/06/1963"), "Duangkaew Piveteau");

		employees.add(emp1);
		employees.add(emp2);
        String deptNo="d006";
		 
		when(service.getAllEmployeesByDeptNo(anyString())).thenReturn(employees);
		
		
		assertEquals(controller.getAllEmployeesByDeptNo(deptNo).getBody().size(), employees.size());
		assertEquals(controller.getAllEmployeesByDeptNo(deptNo).getBody(), employees);
		assertEquals(controller.getAllEmployeesByDeptNo(deptNo).getStatusCode(), HttpStatus.OK);

		
		 
		
		
		/*
		 * MvcResult mvcResult = mockMvc.perform(get("/employees/dept/{deptNo}",deptNo))
		 * .andReturn();
		 * 
		 * System.out.println("mvcresult-----------> "+mvcResult.getResponse().
		 * getContentAsString());
		 */
		
		
		
		/*
		 * mockMvc.perform( MockMvcRequestBuilders
		 * .get("/employees/dept/{deptNo}",deptNo) .accept(MediaType.APPLICATION_JSON))
		 * .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		 * .andExpect(status().isOk());
		 */
}
	
	
	@Test
	public void getEmployeesFromHiredafterDateAndsalaryTest() throws Exception {
		

		List<Employee> employees=new ArrayList<>();
		
		Employee emp1=new Employee(10005,FORMAT.parse("19/04/1932"),"Kyoichi","Maliniak","M",FORMAT.parse("21/01/1955"));
	
		Integer vSalary=60000;
		String date="1986-12-01";
		
		employees.add(emp1);
		
		when(service.getEmployeesFromHiredafterDateAndsalary(eq(vSalary), eq(date))).thenReturn(employees);
		
		/*
		 * mockMvc.perform( MockMvcRequestBuilders
		 * .get("/employees/hiredate/{somedate}/salary/{salary}",date,vSalary)
		 * .accept(MediaType.APPLICATION_JSON))
		 * .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		 * .andExpect(status().isOk());
		 */
		
     
		
		
		assertEquals(controller.getEmployeesFromHiredafterDateAndsalary(vSalary, date).getBody().size(), employees.size());
		assertEquals(controller.getEmployeesFromHiredafterDateAndsalary(vSalary, date).getBody(), employees);
		assertEquals(controller.getEmployeesFromHiredafterDateAndsalary(vSalary, date).getStatusCode(), HttpStatus.OK);
		
	}	
	
	
	@Test
	public void deleteEmployeeOnHireDate() throws Exception {
		String date="1986-12-01";
		
		controller.deleteEmployeeOnHireDate(date);
		verify(service).deleteEmployeeOnHireDate(date);
		
		
		/*
		 * mockMvc.perform( MockMvcRequestBuilders.delete(
		 * "/employees/salaries/employee/hiredate/{somedate}", date) )
		 * .andExpect(status().isNoContent());
		 */
		
		assertEquals(controller.deleteEmployeeOnHireDate(date).getStatusCode(), HttpStatus.NO_CONTENT);
		
	}
	
}
