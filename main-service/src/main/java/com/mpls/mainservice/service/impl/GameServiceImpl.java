package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.config.ConfigGame;
import com.mpls.mainservice.dto.CountryBaseDto;
import com.mpls.mainservice.dto.GameFullInfo;
import com.mpls.mainservice.dto.NominationDto;
import com.mpls.mainservice.dto.command.UserCountryCommandDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.*;
import com.mpls.mainservice.service.CountryService;
import com.mpls.mainservice.service.GameService;
import com.mpls.mainservice.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.stream.Collectors;
import java.util.List;

import static com.mpls.builder.Builder.map;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ConfigGame configGame;

    @Autowired
    private UserCountryRepository userCountryRepository;

    @Autowired
    private DonatedMoneyRepository donatedMoneyRepository;

    @Autowired
    private WebSocketService webSocketService;

    @Override
    public Page<GameFullInfo> findAllMyGame(Pageable pageable, Principal principal) {
        return gameRepository.findAllMyGame(principal.getName(), pageable).map(gameModel -> parseGameFullInfo(gameModel, principal));
    }

    @Override
    public GameModel create(Long countCountry) {
        GameModel gameModel = new GameModel();
        gameModel.setCurrentStep(Step.START);
        gameModel.setId(save(gameModel).getId());
        gameModel.setCountry(countryService.create(gameModel, countCountry));
        gameModel = findOne(gameModel.getId());
        webSocketService.sendNewGame();
        return gameModel;
    }

    @Override
    public List<GameModel> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Page<GameFullInfo> findAll(Pageable pageable, Principal principal) {
        return gameRepository.findAll(pageable).map(gameModel -> parseGameFullInfo(gameModel, principal));
    }

    @Override
    public Page<GameFullInfo> findAllFreeGame(Pageable pageable, Principal principal) {
        return gameRepository.findAllByCurrentStep(Step.START, pageable).map(gameModel -> parseGameFullInfo(gameModel, principal));
    }

    public GameFullInfo parseGameFullInfo(GameModel gameModel, Principal principal) {
        return new GameFullInfo()
                .setId(gameModel.getId())
                .setRelevantNumber(gameModel.getCurrentStep().next())
                .setCountry(parseCountryBaseDto(gameModel.getCountry(), principal))
                .setMyGame(userCountryRepository.countByCountry_Game_IdAndUser_Login(gameModel.getId(), principal.getName()) > 0)
                .setCountUser(userCountryRepository.countByCountry_Game_Id(gameModel.getId()));
    }

    public CountryBaseDto parseCountryBaseDto(CountryModel countryModel, Principal principal) {
        return new CountryBaseDto()
                .setId(countryModel.getId())
                .setColor(countryModel.getColor())
                .setName(countryModel.getName())
                .setPhoto(countryModel.getPhoto())
                .setColor(countryModel.getColor())
                .setWeaponTechnology(countryModel.getWeaponTechnology())
                .setMyGame(userCountryRepository.countByCountry_IdAndUser_Login(countryModel.getId(), principal.getName()) > 0)
                .setUserCountry(map(countryModel.getUserCountry(), UserCountryCommandDto.class));
    }

    public List<CountryBaseDto> parseCountryBaseDto(List<CountryModel> countryModel, Principal principal) {
        return countryModel.stream().map(countryModel1 -> parseCountryBaseDto(countryModel1, principal)).collect(Collectors.toList());
    }

    @Override
    public GameModel findOne(Long id) {
        return gameRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public GameFullInfo findOne(Long id, Principal principal) {
        return parseGameFullInfo(findOne(id), principal);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);

    }

    @Override
    public GameModel save(GameModel gameModel) {
        return gameRepository.save(gameModel);
    }


}
