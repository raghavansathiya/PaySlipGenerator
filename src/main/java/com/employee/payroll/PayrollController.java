package com.employee.payroll;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.payroll.data.Employee;

@Validated
@RestController
public class PayrollController {

	@Autowired
	private PayrollService payrollService;

	@PostMapping("/generatepayslip")
	public List<Employee> getneratePaySlip(
			@RequestBody List<@Valid Employee> employee) {
		return payrollService.generatePaySlip(employee);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handle(HttpMessageNotReadableException e) throws Exception {
		throw new Exception("Request contains invalid input");
	}
}
