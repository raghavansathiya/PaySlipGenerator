package com.employee.payroll;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.payroll.data.Employee;
import com.employee.payroll.tax.TaxBracket;
import com.employee.payroll.tax.TaxCategoryInitializer;

@Service
public class PayrollService {

	@Autowired
	private TaxCategoryInitializer taxCategoryInit;

	/**
	 * Method that acts on the list of employees and generates payslips for them
	 * 
	 * @param employees
	 * @return
	 */
	public List<Employee> generatePaySlip(List<Employee> employees) {
		List<Employee> employeePayslip = new ArrayList<Employee>();

		for (Employee employee : employees) {
			BigDecimal grossIncome = getGrossIncome(employee.getSalary());
			BigDecimal tax = generateIncomeTax(employee.getSalary());
			BigDecimal netIncome = getNetIncome(tax, grossIncome);
			BigDecimal superComponent = getSuper(grossIncome,
					employee.getSuperComponent());
			employeePayslip.add(new Employee(
					employee.getFirstName(), employee.getLastName(),
					superComponent, employee.getSalary(),
					employee.getPayment_start_date(), grossIncome, tax,
					netIncome));
		}
		return employeePayslip;
	}
	
	/**
	 * 
	 * @param grossIncome
	 * @param super_rate
	 * @return
	 */
	public BigDecimal getSuper(BigDecimal grossIncome, BigDecimal super_rate) {
		return grossIncome.multiply(super_rate).divide(new BigDecimal(100))
				.setScale(0, RoundingMode.HALF_UP);
	}

	/**
	 * 
	 * @param salary
	 * @return
	 */
	public BigDecimal getGrossIncome(BigDecimal salary) {
		return new BigDecimal(Math.round(salary.doubleValue() / 12));
	}

	/**
	 * 
	 * @param tax
	 * @param grossIncome
	 * @return
	 */
	public BigDecimal getNetIncome(BigDecimal tax, BigDecimal grossIncome) {
		return grossIncome.subtract(tax);
	}

	/**
	 * 
	 * @param taxableIncome
	 * @return
	 */
	public BigDecimal generateIncomeTax(BigDecimal taxableIncome) {
		BigDecimal tax = BigDecimal.ZERO;
		for (TaxBracket taxBracket : taxCategoryInit.getTaxBrackets()) {
			if (taxBracket.getMaxIncome().signum() != -1
					&& taxableIncome.compareTo(taxBracket.getMaxIncome()) >= 0)
				tax = tax.add(taxBracket.getMaxIncome()
						.subtract(taxBracket.getMinIncome()).multiply(taxBracket
								.getTaxRate().divide(new BigDecimal(100))));
			else if (taxBracket.getMaxIncome().signum() != -1
					&& taxableIncome.compareTo(taxBracket.getMinIncome()) >= 0)
				tax = tax.add(taxableIncome.subtract(taxBracket.getMinIncome())
						.add(new BigDecimal(1)).multiply(taxBracket.getTaxRate()
								.divide(new BigDecimal(100))));
			else
				break;
		}
		return new BigDecimal(Math.round(tax.doubleValue() / 12));
	}

}
