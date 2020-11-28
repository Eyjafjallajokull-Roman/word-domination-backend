package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.command.CountryFullInfoCommandDto;
import com.mpls.mainservice.dto.statistic.CountryStatisticDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.GameModel;

import java.security.Principal;
import java.util.List;

public interface CountryService {

    List<CountryStatisticDto> findAllGlobalStatistic(Long idGame);

    CountryFullInfoCommandDto findCountryFullInfoCommandDto(Long idGame, Principal principal);

    CountryModel findByGameIdAndUserLogin(Long idGame, Principal login);

    List<CountryModel> create(GameModel gameModel, Long countCountry);

    List<CountryModel> findAll();

    List<CountryModel> findAllByGame(Long idGame);

    CountryModel findOne(Long id);

    void deleteById(Long id);

    CountryModel save(CountryModel countryModel);
}
