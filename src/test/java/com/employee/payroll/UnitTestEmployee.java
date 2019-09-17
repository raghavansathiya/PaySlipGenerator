package com.employee.payroll;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UnitTestEmployee {
	@JsonProperty(access = Access.READ_WRITE)
	BigDecimal salary;
	
	@JsonProperty(access = Access.READ_WRITE)
	String firstName;
	
	@JsonProperty(access = Access.READ_WRITE)
	String lastName;
}
