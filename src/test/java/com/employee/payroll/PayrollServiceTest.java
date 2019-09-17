package com.employee.payroll;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employee.payroll.tax.TaxBracket;
import com.employee.payroll.tax.TaxCategoryInitializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PayrollServiceTest {
	
	@Autowired
	PayrollService payrollservice;
	
	@Autowired
	TaxCategoryInitializer taxCategoryInit;
	
	@Autowired
	TaxBracket[] taxBracketArray;
	
	@Autowired
	TaxBracket taxBracket;
	
	
	@Test
	public void testGetSuper() {
		assertEquals(payrollservice.getSuper(new BigDecimal(1000), new BigDecimal(9)),new BigDecimal(90));
		assertEquals(payrollservice.getSuper(new BigDecimal(5004), new BigDecimal(9)),new BigDecimal(450));
		assertEquals(payrollservice.getSuper(new BigDecimal(10000), new BigDecimal(10)),new BigDecimal(1000));
		assertEquals(payrollservice.getSuper(new BigDecimal(20000), new BigDecimal(10)),new BigDecimal(2000));
		
	}
	
	@Test
	public void testGetGrossIncome() {
		assertEquals(payrollservice.getGrossIncome(new BigDecimal(60050)),new BigDecimal(5004));
		assertEquals(payrollservice.getGrossIncome(new BigDecimal(120000)),new BigDecimal(10000));
		assertEquals(payrollservice.getGrossIncome(new BigDecimal(108000)),new BigDecimal(9000));
		assertEquals(payrollservice.getGrossIncome(new BigDecimal(180001)),new BigDecimal(15000));
	}
	
	@Test
	public void testGetNetIncome() {
		assertEquals(payrollservice.getNetIncome(new BigDecimal(922), new BigDecimal(5004)), new BigDecimal(4082));
		assertEquals(payrollservice.getNetIncome(new BigDecimal(2669), new BigDecimal(10000)), new BigDecimal(7331));
	}
	
	@Test
	public void testGenerateIncomeTax1() throws JsonParseException, JsonMappingException, IOException, NoSuchFieldException, SecurityException {
		Mockito.when(taxCategoryInit.getTaxBrackets()).thenReturn(taxBracketArray);
		Mockito.when(taxBracket.getMinIncome()).thenReturn(new BigDecimal(37001));
		Mockito.when(taxBracket.getMaxIncome()).thenReturn(new BigDecimal(87000));
		Mockito.when(taxBracket.getTaxRate()).thenReturn(new BigDecimal(32.5));
		assertEquals(payrollservice.generateIncomeTax(new BigDecimal(60050)), new BigDecimal(624));
	}
	
	@Test
	public void testGenerateIncomeTax2() throws JsonParseException, JsonMappingException, IOException, NoSuchFieldException, SecurityException {
		Mockito.when(taxCategoryInit.getTaxBrackets()).thenReturn(taxBracketArray);
		Mockito.when(taxBracket.getMinIncome()).thenReturn(new BigDecimal(180001));
		Mockito.when(taxBracket.getMaxIncome()).thenReturn(new BigDecimal(-1));
		Mockito.when(taxBracket.getTaxRate()).thenReturn(new BigDecimal(45));
		assertEquals(payrollservice.generateIncomeTax(new BigDecimal(60050)), new BigDecimal(0));
	}
	
	@Test
	public void testGenerateIncomeTax3() throws JsonParseException, JsonMappingException, IOException, NoSuchFieldException, SecurityException {
		Mockito.when(taxCategoryInit.getTaxBrackets()).thenReturn(taxBracketArray);
		Mockito.when(taxBracket.getMinIncome()).thenReturn(new BigDecimal(0));
		Mockito.when(taxBracket.getMaxIncome()).thenReturn(new BigDecimal(18200));
		Mockito.when(taxBracket.getTaxRate()).thenReturn(new BigDecimal(0));
		assertEquals(payrollservice.generateIncomeTax(new BigDecimal(100050)), new BigDecimal(0));
	}
	
	@Test
	public void testGenerateIncomeTax4() throws JsonParseException, JsonMappingException, IOException, NoSuchFieldException, SecurityException {
		Mockito.when(taxCategoryInit.getTaxBrackets()).thenReturn(taxBracketArray);
		Mockito.when(taxBracket.getMinIncome()).thenReturn(new BigDecimal(180001));
		Mockito.when(taxBracket.getMaxIncome()).thenReturn(new BigDecimal(-1));
		Mockito.when(taxBracket.getTaxRate()).thenReturn(new BigDecimal(45));
		assertEquals(payrollservice.generateIncomeTax(new BigDecimal(180001)), new BigDecimal(0));
	}
}
