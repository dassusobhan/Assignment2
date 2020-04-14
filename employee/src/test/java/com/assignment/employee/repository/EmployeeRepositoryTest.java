package com.assignment.employee.repository;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.assignment.employee.exception.EmployeeGenericException;
import com.assignment.employee.models.Employee;
import com.assignment.employee.models.EmployeeModel;

@ExtendWith(SpringExtension.class)
public class EmployeeRepositoryTest {

	@InjectMocks
	EmployeeRepository repository;

	@Mock
	NamedParameterJdbcTemplate jdbcTemplate;

	static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	static Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);

    @Test
	public void getAllEmployeesByDeptNoTest() throws ParseException, SQLException {

		List<EmployeeModel> employees = new ArrayList<>();
		EmployeeModel emp1 = new EmployeeModel(1, FORMAT.parse("31/12/1998"), "employee1");

		employees.add(emp1);

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		RowMapper rowMapper = mock(RowMapper.class);

		when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
				.thenReturn(employees);

		// test
		List<EmployeeModel> empList = jdbcTemplate.query("", parameters,
				(resultSet, i) -> repository.toEmployeeModel(resultSet));

		List<EmployeeModel> empList1 = repository.getAllEmployeesByDeptNo("");

		assertEquals(1, empList.size());
		assertNotNull(empList1);
		assertEquals(1, empList1.size());

	}

	@Test
	public void getEmployeesFromHiredafterDateAndsalary_SuccessTest() throws ParseException {

		List<Employee> employees = new ArrayList<>();

		Employee emp1 = new Employee(1, FORMAT.parse("31/12/1998"), "Sumit", "Mondal", "M", FORMAT.parse("12/05/2018"));
		employees.add(emp1);

		Integer vSalary = 60000;
		String someDate = "31/05/2017";

		assertThat(vSalary, instanceOf(Integer.class));
		assertThat(someDate, instanceOf(String.class));

		MapSqlParameterSource parameters = new MapSqlParameterSource();

		RowMapper rowMapper = mock(RowMapper.class);

		when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
				.thenReturn(employees);

		List<Employee> empList = jdbcTemplate.query("", parameters, (resultSet, i) -> repository.toEmployee(resultSet));

		List<Employee> empList1 = repository.getEmployeesFromHiredafterDateAndsalary(vSalary, someDate);

		assertEquals(1, empList.size());
		assertNotNull(empList1);
		assertEquals(1, empList1.size());

		logger.info("empList -> {} and empList1--> {}", empList, empList1);

		for (Employee emp : empList) {
			logger.info(
					"emp[empNo -> {}, birth-date -> {}, firstname->{}, lastname-> {}, gender-> {}, hireDate-> {}] in empList ",
					emp.getEmp_no(), emp.getBirth_date(), emp.getFirst_name(), emp.getLast_name(), emp.getGender(),
					emp.getHire_date());
		}
		for (Employee emp : empList1) {
			logger.info(
					"emp[empNo -> {}, birth-date -> {}, firstname->{}, lastname-> {}, gender-> {}, hireDate-> {}] in empList1 ",
					emp.getEmp_no(), emp.getBirth_date(), emp.getFirst_name(), emp.getLast_name(), emp.getGender(),
					emp.getHire_date());

		}

	}

	@Test
	public void getEmployeesFromHiredafterDateAndsalary_ExceptionTest() throws ParseException {

		List<Employee> employees = new ArrayList<>();

		Employee emp1 = new Employee(1, FORMAT.parse("31/12/1998"), "Sumit", "Mondal", "M", FORMAT.parse("12/05/2018"));
		employees.add(emp1);

		Integer vSalary = 60000;
		String someDate = null;
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();


		RowMapper rowMapper = mock(RowMapper.class);

		
		  when(jdbcTemplate.query(anyString(), any(MapSqlParameterSource.class),
		  any(RowMapper.class))) .thenThrow(EmployeeGenericException.class);
		 
		
		Assertions.assertThrows(EmployeeGenericException.class, () -> {
			repository.getEmployeesFromHiredafterDateAndsalary(vSalary, someDate);
		  });
		
		

		
	}
	@Test
	public void deleteEmployeeOnHireDate_success() {
	
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		when(jdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenReturn(1);
		
		int count = jdbcTemplate.update("", parameters);
		assertEquals(1, count);
		
	}
	
	@Test
	public void deleteEmployeeOnHireDate_Exception() {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		when(jdbcTemplate.update(anyString(), any(MapSqlParameterSource.class))).thenThrow(EmployeeGenericException.class);
	
		
		Assertions.assertThrows(EmployeeGenericException.class, () -> {
			repository.deleteEmployeeOnHireDate("");
		  });
		
	}



}