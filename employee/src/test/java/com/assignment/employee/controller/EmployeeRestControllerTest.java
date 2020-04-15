package com.assignment.employee.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.employee.models.Employee;
import com.assignment.employee.models.EmployeeModel;
import com.assignment.employee.service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	EmployeeController controller;

	@MockBean
	EmployeeService service;

	static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void getAllEmployeesByDeptNoTest() throws Exception {
		List<EmployeeModel> employees = new ArrayList<>();
		EmployeeModel emp1 = new EmployeeModel(10009, FORMAT.parse("19/04/1952"), "Sumant Peac");
		EmployeeModel emp2 = new EmployeeModel(10010, FORMAT.parse("01/06/1963"), "Duangkaew Piveteau");

		employees.add(emp1);
		employees.add(emp2);
		String deptNo = "d006";

		when(service.getAllEmployeesByDeptNo(anyString())).thenReturn(employees);
		String expectedJson = "[{\"emp_no\":10009,\"birth_date\":\"1952-04-18T18:30:00.000+0000\",\"name\":\"Sumant Peac\"},"
				+ "{\"emp_no\":10010,\"birth_date\":\"1963-05-31T18:30:00.000+0000\",\"name\":\"Duangkaew Piveteau\"}]";

		this.mockMvc.perform(get("/employees/dept/{deptNo}", deptNo).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(expectedJson)));

	}

	@Test
	public void getEmployeesFromHiredafterDateAndsalaryTest() throws Exception {

		List<Employee> employees = new ArrayList<>();

		Employee emp1 = new Employee(10005, FORMAT.parse("19/04/1932"), "Kyoichi", "Maliniak", "M",
				FORMAT.parse("21/01/1955"));

		Integer vSalary = 60000;
		String date = "1986-12-01";

		employees.add(emp1);

		when(service.getEmployeesFromHiredafterDateAndsalary(eq(vSalary), eq(date))).thenReturn(employees);

		String expectedJson = "[{\"emp_no\":10005,\"birth_date\":\"1932-04-18T18:30:00.000+0000\",\"first_name\":\"Kyoichi\","
				+ "\"last_name\":\"Maliniak\",\"gender\":\"M\",\"hire_date\":\"1955-01-20T18:30:00.000+0000\"}]";

		this.mockMvc
				.perform(get("/employees/hiredate/{somedate}/salary/{salary}", date, vSalary)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(expectedJson)));

	}

	@Test
	public void deleteEmployeeOnHireDateTest() throws Exception {
		String date = "1986-12-01";

		this.mockMvc.perform(
				delete("/employees/salaries/employee/hiredate/{somedate}", date)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}

}
