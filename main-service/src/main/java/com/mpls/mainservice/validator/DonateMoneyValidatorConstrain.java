package com.mpls.mainservice.validator;

import com.mpls.mainservice.dto.DonatedMoneyCreateDto;
import com.mpls.mainservice.service.CalculationService;
import com.mpls.mainservice.service.CountryService;
import com.mpls.mainservice.validator.annotation.DonateMoneyValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class DonateMoneyValidatorConstrain implements ConstraintValidator<DonateMoneyValidator, DonatedMoneyCreateDto> {

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private CountryService countryService;

    @Override
    public boolean isValid(DonatedMoneyCreateDto createDto, ConstraintValidatorContext constraintValidatorContext) {
        Long balance = calculationService.totalEconomy(countryService.findOne(createDto.getTo().getId()));
        return balance >= createDto.getPrice();
    }

    @Override
    public void initialize(DonateMoneyValidator constraintAnnotation) {

    }
}
