package com.employee.payroll;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.employee.payroll.tax.TaxBracket;
import com.employee.payroll.tax.TaxCategoryInitializer;

@Profile("test")
@Configuration
public class TaxCategoryConfiguration {
    @Bean
    @Primary
    public TaxCategoryInitializer mockTaxCategoryInitializer() {
        return Mockito.mock(TaxCategoryInitializer.class);
    }
    
    @Bean
    @Primary
    public TaxBracket mockTaxBracjet() {
        return Mockito.mock(TaxBracket.class);
    }
}