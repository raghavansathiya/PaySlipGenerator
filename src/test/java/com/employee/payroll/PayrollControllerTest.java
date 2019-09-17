package com.employee.payroll;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.payroll.data.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PayrollControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void testSuccessfulResponse() throws Exception {
		SimpleModule simpleModule = new SimpleModule();
		SimpleModule employeeModule = simpleModule
				.setMixInAnnotation(Employee.class, UnitTestEmployee.class);
		objectMapper.registerModule(employeeModule);
		Employee emp1 = new Employee("David", "Jones", new BigDecimal(9.5),
				new BigDecimal(10000), "01 December - 31 December", null, null,
				null);
		List<Employee> employees = Arrays.asList(emp1);
		this.mockMvc
				.perform(post("/generatepayslip")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employees)))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testFirstNameCheckMinValueError() throws Exception {
		SimpleModule employeeModule = new SimpleModule();
		employeeModule.setMixInAnnotation(Employee.class,
				UnitTestEmployee.class);
		objectMapper.registerModule(employeeModule);
		Employee emp1 = new Employee("", "Jones", new BigDecimal(9.5),
				new BigDecimal(10000), "01 December - 31 December", null, null,
				null);
		List<Employee> employees = Arrays.asList(emp1);
		this.mockMvc
				.perform(post("/generatepayslip")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employees)))
				.andExpect(content()
						.string(containsString("should be between 1 and 32")));
	}
	
	@Test
	public void testLastNameCheckMinValueError() throws Exception {
		SimpleModule employeeModule = new SimpleModule();
		employeeModule.setMixInAnnotation(Employee.class,
				UnitTestEmployee.class);
		objectMapper.registerModule(employeeModule);
		Employee emp1 = new Employee("David", "", new BigDecimal(9.5),
				new BigDecimal(10000), "01 December - 31 December", null, null,
				null);
		List<Employee> employees = Arrays.asList(emp1);
		this.mockMvc
				.perform(post("/generatepayslip")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employees)))
				.andExpect(content()
						.string(containsString("should be between 1 and 32")));
	}
	
	
	@Test
	public void testFirstNameCheckMaxValueError() throws Exception {
		SimpleModule employeeModule = new SimpleModule();
		employeeModule.setMixInAnnotation(Employee.class,
				UnitTestEmployee.class);
		objectMapper.registerModule(employeeModule);
		Employee emp1 = new Employee("123456789012345678901234567890123", "Jones", new BigDecimal(9.5),
				new BigDecimal(10000), "01 December - 31 December", null, null,
				null);
		List<Employee> employees = Arrays.asList(emp1);
		this.mockMvc
				.perform(post("/generatepayslip")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employees)))
				.andExpect(content()
						.string(containsString("should be between 1 and 32")));
	}
	
	@Test
	public void testLastNameCheckMaxValueError() throws Exception {
		SimpleModule employeeModule = new SimpleModule();
		employeeModule.setMixInAnnotation(Employee.class,
				UnitTestEmployee.class);
		objectMapper.registerModule(employeeModule);
		Employee emp1 = new Employee("David", "123456789012345678901234567890123", new BigDecimal(9.5),
				new BigDecimal(10000), "01 December - 31 December", null, null,
				null);
		List<Employee> employees = Arrays.asList(emp1);
		this.mockMvc
				.perform(post("/generatepayslip")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employees)))
				.andExpect(content()
						.string(containsString("should be between 1 and 32")));
	}
	
	@Test
	public void testSalaryCheck() throws Exception {
		SimpleModule simpleModule = new SimpleModule();
		SimpleModule employeeModule = simpleModule
				.setMixInAnnotation(Employee.class, UnitTestEmployee.class);
		objectMapper.registerModule(employeeModule);
		Employee emp1 = new Employee("David", "Jones", new BigDecimal(9.5),
				new BigDecimal(-1), "01 December - 31 December", null, null,
				null);
		List<Employee> employees = Arrays.asList(emp1);
		this.mockMvc
				.perform(post("/generatepayslip")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employees)))
				.andExpect(content()
						.string(containsString("Salary cannot be less than 0")));
	}
	
	@Test
	public void testSuperCheck() throws Exception {
		SimpleModule simpleModule = new SimpleModule();
		SimpleModule employeeModule = simpleModule
				.setMixInAnnotation(Employee.class, UnitTestEmployee.class);
		objectMapper.registerModule(employeeModule);
		Employee emp1 = new Employee("David", "Jones", new BigDecimal(101),
				new BigDecimal(0), "01 December - 31 December", null, null,
				null);
		List<Employee> employees = Arrays.asList(emp1);
		this.mockMvc
				.perform(post("/generatepayslip")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employees)))
				.andExpect(content()
						.string(containsString("Super cannot be greather than 100")));
	}
	
	@Test
	public void testPayMonthCheck() throws Exception {
		SimpleModule simpleModule = new SimpleModule();
		SimpleModule employeeModule = simpleModule
				.setMixInAnnotation(Employee.class, UnitTestEmployee.class);
		objectMapper.registerModule(employeeModule);
		Employee emp1 = new Employee("David", "Jones", new BigDecimal(101),
				new BigDecimal(0), "01 December - 31 January", null, null,
				null);
		List<Employee> employees = Arrays.asList(emp1);
		this.mockMvc
				.perform(post("/generatepayslip")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employees)))
				.andExpect(content()
						.string(containsString("is not valid. Payment month should be in the same month")));
	}
	
}
