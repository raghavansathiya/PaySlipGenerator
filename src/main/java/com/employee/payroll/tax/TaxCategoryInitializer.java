package com.employee.payroll.tax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.employee.payroll.data.Employee;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TaxCategoryInitializer {

	TaxInfo taxInfo;
	ObjectMapper obj = new ObjectMapper();

	@Autowired
	private TaxCategoryService taxCateogryService;

	@PostConstruct
	public void init()
			throws JsonParseException, JsonMappingException, IOException {
		taxInfo = obj.readValue(
				new File(taxCateogryService.taxCategoryConfigdir),
				TaxInfo.class);
	}

	public TaxBracket[] getTaxBrackets() {
		return taxInfo.getTaxBrackets();
	}

	public String getJsonString(Employee employee)
			throws JsonProcessingException {
		return obj.writeValueAsString(employee);
	}

}
