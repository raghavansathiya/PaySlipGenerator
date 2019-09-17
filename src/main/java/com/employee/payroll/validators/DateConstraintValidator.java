package com.employee.payroll.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator
		implements ConstraintValidator<DateConstraint, String> {

	@Override
	public boolean isValid(String paymentStartDate,
			ConstraintValidatorContext context) {

		String[] payDates = paymentStartDate.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM");

		try {
			if (sdf.parse(payDates[0]).getMonth() == sdf.parse(payDates[1])
					.getMonth())
				return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

}
