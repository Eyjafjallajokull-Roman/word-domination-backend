package com.mpls.mainservice.service;

import com.mpls.mainservice.model.CityModel;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.enums.Step;

import java.math.BigDecimal;
import java.util.List;

public interface CalculationService {

    Long calculationCountWeapon(CountryModel countryModel);

    BigDecimal calculationEconomyCity(CityModel cityModel, Long gameId);

    BigDecimal calculationEconomyCountry(CountryModel countryModel, Long gameId);

    BigDecimal calculationPointCity(CityModel cityModel);

    Boolean calculationSendDecision(CountryModel countryModel);

    BigDecimal calculationEconomyCity(CityModel cityModel);

    BigDecimal calculationPointCountry(CountryModel countryModel);

    BigDecimal calculationCityProgress(CountryModel countryModel);

    Boolean calculationCityRuined(CityModel cityModel);

    Boolean calculationShieldRuined(CityModel cityModel);

    Long calculationIncomeEconomy(CountryModel countryModel);

    Long calculationSpendingEconomy(CountryModel countryModel);

    Long totalEconomy(CountryModel countryModel);

    BigDecimal collectEcology(CountryModel countryModel, Step step);
    BigDecimal collectEcology(Long idGame, Step step);

}
