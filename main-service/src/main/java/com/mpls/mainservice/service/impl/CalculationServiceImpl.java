package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.config.ConfigGame;
import com.mpls.mainservice.model.CityModel;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.DonatedMoneyModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.*;
import com.mpls.mainservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CalculationServiceImpl implements CalculationService {

    @Autowired
    private DecisionRepository decisionRepository;

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private UsedWeaponRepository usedWeaponRepository;

    @Autowired
    private DonatedMoneyRepository donatedMoneyRepository;

    @Autowired
    private ConfigGame configGame;

    @Autowired
    private EcologyRepository ecologyRepository;

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private UsedWeaponService usedWeaponService;

    @Autowired
    private SanctionRepository sanctionRepository;

    @Autowired
    private UpdateCityRepository updateCityRepository;

    @Autowired
    private EcologyService ecologyService;

    @Autowired
    private CityService cityService;

    @Autowired
    private DecisionService decisionService;

    @Autowired
    private ShieldRepository shieldRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Long calculationCountWeapon(CountryModel countryModel) {
        return weaponRepository.countByDecision_Country_Id(countryModel.getId()) - usedWeaponRepository.countByDecision_Country_Id(countryModel.getId());
    }

    @Override
    public BigDecimal calculationEconomyCity(CityModel cityModel, Long gameId) {
        if (calculationCityRuined(cityModel)) {
            return BigDecimal.valueOf(0);
        } else {
            return (configGame.getDefaultGoldIncome().multiply(configGame.getDefaultCityUpgrade()
                    .add((cityUpdates(cityModel)))))
                    .multiply(ecologyService.collectEcologyByGame(gameId).divide(BigDecimal.valueOf(100)));
        }
    }


    @Override
    public BigDecimal calculationEconomyCountry(CountryModel countryModel, Long gameId) {
        BigDecimal bigDecimal = BigDecimal.valueOf(0);
        for (CityModel c : countryModel.getCity()) {
            bigDecimal = calculationPointCity(c).add(bigDecimal);
        }
        return bigDecimal.divide(BigDecimal.valueOf(countryModel.getCity().size())).setScale(0, RoundingMode.UP);
    }


    @Override
    public BigDecimal calculationPointCity(CityModel cityModel) {
        return calculationEconomyCity(cityModel);
    }

    @Override
    public Boolean calculationSendDecision(CountryModel countryModel) {
        return !decisionService.countByCountryIdAndStep(countryModel.getId(), countryModel.getGame().getCurrentStep().next()).equals(0L);
    }

    @Override
    public BigDecimal calculationEconomyCity(CityModel cityModel) {
        if (cityModel.getRuined()) {
            return BigDecimal.valueOf(0);
        }
        return BigDecimal.valueOf(50).add(
                BigDecimal.valueOf(cityService.countByCityIdAndSessionSessionNumberCollected(cityModel.getId(), cityModel.getCountry().getGame().getCurrentStep()))
                        .multiply(configGame.getGrowthCityUpgrade())).multiply(collectEcology(cityModel.getCountry(), cityModel.getCountry().getGame().getCurrentStep()).divide(BigDecimal.valueOf(100))).setScale(0, RoundingMode.UP);

    }

    @Override
    public BigDecimal calculationPointCountry(CountryModel countryModel) {
        BigDecimal bigDecimal = BigDecimal.valueOf(0);
        for (CityModel c : countryModel.getCity()) {
            bigDecimal = bigDecimal.add(calculationPointCity(c));
        }
        return bigDecimal.divide(BigDecimal.valueOf(countryModel.getCity().size())).setScale(0, RoundingMode.UP);
    }

    @Override
    public BigDecimal calculationCityProgress(CountryModel countryModel) {
        return null;
    }

    private BigDecimal cityUpdates(CityModel cityModel) {
        return configGame.getGrowthCityUpgrade().multiply(BigDecimal.valueOf(cityModel.getUpdateCity().size()));
    }

    @Override
    public Boolean calculationCityRuined(CityModel cityModel) {
        Long countUsedWeaponInCity = decisionRepository.countUsedWeaponInCity(cityModel.getId(), cityModel.getCountry().getGame().getCurrentStep().name());
        if (cityModel.getRuined()) {
            return true;
        } else if (cityModel.getShield() && countUsedWeaponInCity > 1) {
            return true;
        } else if (cityModel.getShield() && countUsedWeaponInCity == 1) {
            return false;
        } else return (!cityModel.getShield()) && countUsedWeaponInCity == 1;
    }

    @Override
    public Boolean calculationShieldRuined(CityModel cityModel) {
        Long countUsedWeaponInCity = decisionRepository.countUsedWeaponInCity(cityModel.getId(), cityModel.getCountry().getGame().getCurrentStep().name());
        if (cityModel.getRuined()) {
            return true;
        } else if (cityModel.getShield() && countUsedWeaponInCity > 1) {
            return true;
        } else if (cityModel.getShield() && countUsedWeaponInCity == 1) {
            return true;
        } else return (!cityModel.getShield());
    }

    @Override
    public Long calculationSpendingEconomy(CountryModel countryModel) {
        return calculationSpendingEconomy(countryModel, countryModel.getGame().getCurrentStep().next()).longValue();
    }

    @Override
    public Long totalEconomy(CountryModel countryModel) {
        return calculationIncomeEconomy(countryModel) - calculationSpendingEconomy(countryModel);
    }

    private BigDecimal calculationSpendingEconomy(CountryModel countryModel, Step step) {
        Long countEcology = 0L;
        Long countCreateWeapon = 0L;
        Long countCreateShield = 0L;
        Long countUpgradeCity = 0L;
        Long weaponTechnology = 0L;
        AtomicReference<BigDecimal> price = new AtomicReference<>(BigDecimal.ZERO);
        Step sn = Step.START;
        do {
            countEcology += ecologyRepository.countByDecision_Country_IdAndDecision_Step(countryModel.getId(), sn);
            countCreateShield += shieldRepository.countByCity_Country_IdAndDecision_Step(countryModel.getId(), sn);
            countCreateWeapon += weaponRepository.countByDecision_Country_IdAndDecision_Step(countryModel.getGame().getId(), sn);
            weaponTechnology += decisionRepository.countByCountry_IdAndStepAndWeaponTechnology(countryModel.getId(), sn, true);
            countUpgradeCity += updateCityRepository.countByDecision_Country_IdAndDecision_Step(countryModel.getId(), sn);

            donatedMoneyRepository.findAllByFrom_IdAndStep(countryModel.getId(), sn).forEach(donatedMoneyModel -> {
                price.updateAndGet(bigDecimal -> bigDecimal.add(BigDecimal.valueOf(donatedMoneyModel.getPrice())));
            });
            sn = sn.next();
        } while ((!sn.equals(step.next())) && !step.equals(Step.START));

        return price.get().add(
                BigDecimal.valueOf(countEcology).multiply(configGame.getPriceUpdateEcology())
                        .add(BigDecimal.valueOf(countCreateWeapon).multiply(configGame.getPriceWeapon()))
                        .add(BigDecimal.valueOf(weaponTechnology).multiply(configGame.getPriceWeaponTechnology()))
                        .add(BigDecimal.valueOf(countCreateShield).multiply(configGame.getPriceCreateShield()))
                        .add(BigDecimal.valueOf(countUpgradeCity).multiply(configGame.getPriceUpdateCity())).setScale(0, RoundingMode.UP)
        );
    }

    @Override
    public Long calculationIncomeEconomy(CountryModel countryModel) {
        Step step = Step.FIRST;
        AtomicReference<BigDecimal> income = new AtomicReference<>(configGame.getMoneyStatic());
        while ((!step.equals(countryModel.getGame().getCurrentStep().next())) && !countryModel.getGame().getCurrentStep().equals(Step.START)) {
            donatedMoneyRepository.findAllByTo_IdAndStep(countryModel.getId(), step).forEach(donatedMoneyModel -> {
                income.updateAndGet(bigDecimal ->
                        bigDecimal.add(BigDecimal.valueOf(donatedMoneyModel.getPrice()))
                );
            });
            Step finalStep = step;//todo refactor
            income.updateAndGet(bigDecimal ->
                    bigDecimal.add(calculationCountryEconomy(finalStep, countryModel))
            );
            step = step.next();
        }

        return income.get().setScale(0, RoundingMode.UP).longValue();
    }

    private BigDecimal calculationCountryEconomy(Step step, CountryModel countryModel) {
        AtomicReference<BigDecimal> price = new AtomicReference<>(BigDecimal.ZERO);
        countryModel.getCity().stream().filter(cityModel -> !(cityModel.getRuined() && cityModel.getStepRuined().sequenceNumber() <= step.sequenceNumber())).forEach(cityModel -> {
            price.updateAndGet(bigDecimal -> bigDecimal.add(calculationCityEconomy(step, cityModel)));
        });
        return price.get().multiply(BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(sanctionRepository.countByCountry_IdAndDecision_Step(countryModel.getId(), step)).multiply(BigDecimal.valueOf(10))).divide(BigDecimal.valueOf(100)));
    }

    private BigDecimal calculationCityEconomy(Step currentStep, CityModel cityModel) {
        long countUpgrade = cityModel.getUpdateCity().stream()
                .filter(updateCityModel ->
                        updateCityModel.getDecision().getStep().sequenceNumber() <= currentStep.sequenceNumber()
                )
                .count();
        return configGame.getDefaultCityUpgrade().add(configGame.getGrowthCityUpgrade().multiply(BigDecimal.valueOf(countUpgrade)))
                .divide(configGame.getDivideByOneHundred())
                .multiply(configGame.getDefaultGoldIncome())
                .multiply(collectEcology(cityModel.getCountry(), currentStep)
                        .divide(configGame.getDivideByOneHundred()));
    }

    public BigDecimal collectEcology(CountryModel countryModel, Step step) {
        return collectEcology(countryModel.getGame().getId(), step);
    }

    @Override
    public BigDecimal collectEcology(Long idGame, Step step) {
        return ecologyService.collectEcologyByGame(idGame, step);
    }

}

