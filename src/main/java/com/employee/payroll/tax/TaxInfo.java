package com.employee.payroll.tax;

import java.util.Arrays;

public class TaxInfo {

	String taxYear;
	TaxBracket taxBrackets[];

	@Override
	public String toString() {
		return "TaxInfo [taxYear=" + taxYear + ", taxBrackets="
				+ Arrays.toString(taxBrackets) + "]";
	}

	public String getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}

	public TaxBracket[] getTaxBrackets() {
		return taxBrackets;
	}

	public void setTaxBrackets(TaxBracket... taxBrackets) {
		this.taxBrackets = taxBrackets;
	}

}
