package com.employee.payroll.data;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.servlet.View;

import com.employee.payroll.validators.DateConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;

public class Employee {

	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min = 1, max = 32, message = "${validatedValue} should be between {min} and {max}")
	String firstName;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min = 1, max = 32, message = "${validatedValue} should be between {min} and {max}")
	String lastName;

	String fullName;

	@DecimalMin(value = "0", message = "Salary cannot be less than 0")
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	BigDecimal salary;

	@DecimalMax(value = "100", message = "Super cannot be greather than 100")
	@NotNull
	BigDecimal superComponent;

	@NotNull
	@DateConstraint(message = "${validatedValue} is not valid. Payment month should be in the same month")
	String payment_start_date;

	BigDecimal grossIncome;

	BigDecimal incomeTax;

	BigDecimal netIncome;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public BigDecimal getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(BigDecimal grossIncome) {
		this.grossIncome = grossIncome;
	}

	public BigDecimal getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}

	public BigDecimal getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}

	public Employee(String firstName, String lastName, BigDecimal super_rate,
			BigDecimal salary, String payment_start_date,
			BigDecimal grossIncome, BigDecimal incomeTax,
			BigDecimal netIncome) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = firstName + lastName;
		this.superComponent = super_rate;
		this.salary = salary;
		this.payment_start_date = payment_start_date;
		this.grossIncome = grossIncome;
		this.incomeTax = incomeTax;
		this.netIncome = netIncome;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getSuperComponent() {
		return superComponent;
	}

	public void setSuperComponent(BigDecimal superComponent) {
		this.superComponent = superComponent;
	}

	public String getPayment_start_date() {
		return payment_start_date;
	}

	public void setPayment_start_date(String payment_start_date) {
		this.payment_start_date = payment_start_date;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + ", super_rate=" + superComponent
				+ ", payment_start_date=" + payment_start_date
				+ ", grossIncome=" + grossIncome + ", incomeTax=" + incomeTax
				+ ", netIncome=" + netIncome + "]";
	}
}
