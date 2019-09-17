package com.employee.payroll.tax;

import java.math.BigDecimal;

public class TaxBracket {

	BigDecimal minIncome;
	BigDecimal maxIncome;
	BigDecimal taxRate;

	public BigDecimal getMinIncome() {
		return minIncome;
	}

	public void setMinIncome(BigDecimal minIncome) {
		this.minIncome = minIncome;
	}

	public BigDecimal getMaxIncome() {
		return maxIncome;
	}

	public void setMaxIncome(BigDecimal maxIncome) {
		this.maxIncome = maxIncome;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	@Override
	public String toString() {
		return "TaxBracket [minIncome=" + minIncome + ", maxIncome=" + maxIncome
				+ ", taxRate=" + taxRate + "]";
	}

}
