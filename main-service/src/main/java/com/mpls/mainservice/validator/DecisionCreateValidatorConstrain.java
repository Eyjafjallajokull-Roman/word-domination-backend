package com.mpls.mainservice.validator;

import com.mpls.mainservice.config.ConfigGame;
import com.mpls.mainservice.dto.DonatedMoneyCreateDto;
import com.mpls.mainservice.dto.create.DecisionCreateDto;
import com.mpls.mainservice.dto.create.ShieldCreateDto;
import com.mpls.mainservice.dto.create.UsedWeaponCreateDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.repository.DecisionRepository;
import com.mpls.mainservice.repository.UsedWeaponRepository;
import com.mpls.mainservice.service.*;
import com.mpls.mainservice.validator.annotation.DecisionCreateValidator;
import com.mpls.mainservice.validator.annotation.DonateMoneyValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.util.Optional.ofNullable;

public class DecisionCreateValidatorConstrain implements ConstraintValidator<DecisionCreateValidator, DecisionCreateDto> {

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ConfigGame configGame;

    @Autowired
    private CityService cityService;

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private UsedWeaponRepository usedWeaponRepository;

    @Autowired
    private DecisionRepository decisionRepository;

    @Override
    public boolean isValid(DecisionCreateDto decisionCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        CountryModel country = countryService.findOne(decisionCreateDto.getCountry().getId());
        return validUseWeapon(decisionCreateDto)
                && validSpendBalance(decisionCreateDto, country)
                && validCreateWeapon(decisionCreateDto, country)
                && canCreateDecision(decisionCreateDto, country)
                && validShield(decisionCreateDto);
    }

    @Override
    public void initialize(DecisionCreateValidator constraintAnnotation) {

    }

    private boolean canCreateDecision(DecisionCreateDto decisionCreateDto, CountryModel countryModel) {
        return !ofNullable(decisionRepository.findByCountry_IdAndStep(decisionCreateDto.getCountry().getId(), countryModel.getGame().getCurrentStep().next())).isPresent();
    }

    private boolean validUseWeapon(DecisionCreateDto decisionCreateDto) {
        if (decisionCreateDto.getUsedWeapon().stream().anyMatch(usedWeaponCreateDto ->
                usedWeaponRepository.countByWeapon_Id(usedWeaponCreateDto.getWeapon().getId()) != 0)) {
            return false;
        }
        return validUseWeapon(decisionCreateDto.getUsedWeapon());
    }

    private boolean validUseWeapon(List<UsedWeaponCreateDto> list) {
        return list.stream().noneMatch(uw1 -> list.stream().filter(uw2 -> uw1.getWeapon().getId().equals(uw2.getWeapon().getId())).count() != 1);
    }

    private boolean validShield(DecisionCreateDto decisionCreateDto) {
        return validShield(decisionCreateDto.getCreateShield());
    }

    private boolean validShield(List<ShieldCreateDto> createShield) {
        return createShield.stream().allMatch(this::validShield);
    }

    private boolean validShield(ShieldCreateDto createShield) {
        return !cityService.findOne(createShield.getCity().getId()).getShield();
    }

    private boolean validSpendBalance(DecisionCreateDto decisionCreateDto, CountryModel country) {
        Long balance = calculationService.totalEconomy(country);
        return collectSpendBalance(decisionCreateDto, country) <= balance;
    }

    private Long collectSpendBalance(DecisionCreateDto decisionCreateDto, CountryModel country) {
        BigDecimal priceCreateWeapon = BigDecimal.valueOf(decisionCreateDto.getCountCreateWeapon()).multiply(configGame.getPriceWeapon());
        BigDecimal priceCreateShield = BigDecimal.valueOf(decisionCreateDto.getCreateShield().size()).multiply(configGame.getPriceCreateShield());
        BigDecimal priceCreateWeaponTechnology = BigDecimal.valueOf(0);
        BigDecimal priceUpdateCity = BigDecimal.valueOf(decisionCreateDto.getUpdateCity().size()).multiply(configGame.getPriceUpdateCity());
        BigDecimal priceEcology = BigDecimal.valueOf(0);
        if (decisionCreateDto.getCreateEcology()) {
            priceCreateShield = configGame.getPriceUpdateEcology();
        }
        if (decisionCreateDto.getWeaponTechnology() && !country.getWeaponTechnology()) {
            priceCreateWeaponTechnology = configGame.getPriceWeaponTechnology();
        }
        return priceCreateWeapon.add(priceCreateShield).add(priceCreateWeaponTechnology).add(priceUpdateCity).add(priceEcology).setScale(0, RoundingMode.UP).longValue();
    }

    private boolean validCreateWeapon(DecisionCreateDto decisionCreateDto, CountryModel country) {
        if (decisionCreateDto.getCountCreateWeapon() > 3) {
            return false;
        } else if (decisionCreateDto.getCountCreateWeapon() > 0 && !country.getWeaponTechnology()) {
            return false;
        } else if (decisionCreateDto.getCountCreateWeapon() > 0 && country.getWeaponTechnology()) {
            return true;
        }
        return true;
    }
}
