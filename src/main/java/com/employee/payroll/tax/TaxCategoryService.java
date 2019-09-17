package com.employee.payroll.tax;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TaxCategoryService {
	
	@Value("${json.config.folder}")
	public String taxCategoryConfigdir;
	
	public String getTaxCategoryConfigdir() {
		return taxCategoryConfigdir;
	}

	public void setTaxCategoryConfigdir(String taxCategoryConfigdir) {
		this.taxCategoryConfigdir = taxCategoryConfigdir;
	}

	public String getConfigDir() {
		return taxCategoryConfigdir;
	}	
	
}
