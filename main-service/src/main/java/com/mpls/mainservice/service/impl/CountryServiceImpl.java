package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.config.ConfigGame;
import com.mpls.mainservice.dto.WeaponIdDto;
import com.mpls.mainservice.dto.command.CityCommandDto;
import com.mpls.mainservice.dto.command.CountryFullInfoCommandDto;
import com.mpls.mainservice.dto.command.SanctionCommandDto;
import com.mpls.mainservice.dto.statistic.CityStatisticDto;
import com.mpls.mainservice.dto.statistic.CountryStatisticDto;
import com.mpls.mainservice.model.CityModel;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.repository.CountryRepository;
import com.mpls.mainservice.repository.SanctionRepository;
import com.mpls.mainservice.repository.UpdateCityRepository;
import com.mpls.mainservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.builder.Builder.map;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ConfigGame configGame;
    @Autowired
    private CityService cityService;
    @Autowired
    private UpdateCityRepository updateCityRepository;
    @Autowired
    private CalculationService calculationService;

    @Autowired
    private WeaponService weaponService;
    @Autowired
    private EcologyService ecologyService;

    @Autowired
    private DecisionService decisionService;

    @Autowired
    private GameService gameService;

    @Autowired
    private SanctionRepository sanctionRepository;

    @Override
    public CountryFullInfoCommandDto findCountryFullInfoCommandDto(Long idGame, Principal principal) {
        CountryModel countryModel = findByGameIdAndUserLogin(idGame, principal);
        CountryFullInfoCommandDto dto = map(countryModel, CountryFullInfoCommandDto.class)
                .setNumberOfWeapon(calculationService.calculationCountWeapon(countryModel))
                .setEconomy(BigDecimal.valueOf(calculationService.totalEconomy(countryModel)))
                .setSanctions(map(sanctionRepository.findByStepAndCountry(countryModel.getGame().getCurrentStep().name(), countryModel.getId()), SanctionCommandDto.class));
        dto.setCity(countryModel.getCity().stream().map(cityModel ->
                map(cityModel, CityCommandDto.class).setEconomy(calculationService.calculationEconomyCity(cityModel))
        ).collect(Collectors.toList()));
        dto.setWeapon(map(weaponService.findALlFreeWeapon(countryModel.getId()), WeaponIdDto.class));
        dto.setEcology(ecologyService.collectEcologyByGame(idGame).setScale(0, RoundingMode.UP).longValue());
        dto.setStep(countryModel.getGame().getCurrentStep().next());
        dto.setSendDecision(calculationService.calculationSendDecision(countryModel));
        return dto;
    }

    @Override
    public List<CountryStatisticDto> findAllGlobalStatistic(Long idGame) {
        return findAllByGame(idGame).stream()
                .map(this::parseGlobalStatistic)
                .collect(Collectors.toList());
    }


    @Override
    public CountryModel findByGameIdAndUserLogin(Long idGame, Principal login) {
        return countryRepository.findByGameIdAndUserLogin(idGame, login.getName());
    }

    @Override
    public List<CountryModel> create(GameModel gameModel, Long countCountry) {
        return configGame.getCountryList().entrySet().stream().limit(countCountry).map(a -> {
            CountryModel countryModel = countryRepository.save(
                    new CountryModel()
                            .setColor(a.getValue().getColor())
                            .setName(a.getValue().getName())
                            .setPhoto(a.getValue().getPhoto())
                            .setGame(gameModel)
                            .setWeaponTechnology(false)
            );
            return countryModel.setCity(cityService.create(a.getValue(), countryModel));
        }).collect(Collectors.toList());
    }

    @Override
    public List<CountryModel> findAll() {
        return countryRepository.findAll();
    }


    @Override
    public List<CountryModel> findAllByGame(Long idGame) {
        return countryRepository.findAllByGame_Id(idGame);
    }

    @Override
    public CountryModel findOne(Long id) {
        return countryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public CountryModel save(CountryModel countryModel) {
        return countryRepository.save(countryModel);
    }


    private CountryStatisticDto parseGlobalStatistic(CountryModel countryModel) {
        return map(countryModel, CountryStatisticDto.class)
                .setPoint(calculationService.calculationPointCountry(countryModel).longValue())
                .setDecision(calculationService.calculationSendDecision(countryModel))
                .setCity(parseCityStatistic(countryModel.getCity()));
    }

    private List<CityStatisticDto> parseCityStatistic(List<CityModel> cityModel) {
        return cityModel.stream().map(this::parseCityStatistic).collect(Collectors.toList());
    }

    private CityStatisticDto parseCityStatistic(CityModel cityModel) {
        return map(cityModel, CityStatisticDto.class)
                .setEconomy(calculationService.calculationEconomyCity(cityModel))
                .setPoint(calculationService.calculationPointCity(cityModel));
    }
}
