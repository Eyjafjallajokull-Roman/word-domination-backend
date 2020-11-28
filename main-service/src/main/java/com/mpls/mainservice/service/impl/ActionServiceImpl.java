package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.CityRepository;
import com.mpls.mainservice.repository.CountryRepository;
import com.mpls.mainservice.repository.DecisionRepository;
import com.mpls.mainservice.repository.ShieldRepository;
import com.mpls.mainservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private GameService gameService;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CalculationService calculationService;
    @Autowired
    private ShieldRepository shieldRepository;
    @Autowired
    private DecisionRepository decisionRepository;
    @Autowired
    private ShieldService shieldService;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private DiscordService discordService;

    @Override
    public void nextSession(Long idGame) {
        GameModel gameModel = gameService.findOne(idGame);
        if (!decisionRepository.countByStepAndCountry_Game_Id(gameModel.getCurrentStep().next(), idGame).equals(countryRepository.countByGame_Id(idGame))) {
            throw new RuntimeException();
        }
        gameService.save(gameModel.setCurrentStep(gameModel.getCurrentStep().next()));
        cityRepository.findAllByCountry_Game_Id(idGame).stream().map(cityModel -> {
            ofNullable(shieldRepository.findByDecision_StepAndCity_Id(gameModel.getCurrentStep(), cityModel.getId()))
                    .ifPresent(shieldModel -> {
                        cityRepository.save(cityModel.setShield(true));
                    });
            cityRepository.save(cityModel.setRuined(calculationService.calculationCityRuined(cityModel)));
            cityRepository.save(cityModel.setShield(!calculationService.calculationShieldRuined(cityModel)));
            if (cityModel.getRuined()) {
                cityRepository.save(cityModel.setStepRuined(gameModel.getCurrentStep()));
            }
            cityModel.setShieldList(shieldService.ruinedShield(cityModel));
            return cityModel;
        })
                .forEach(cityModel -> {
                    cityRepository.save(cityModel);
                });
        this.webSocketService.sendUpdateGame(gameModel.getId());
        this.webSocketService.sendReloadFreeGame();
        gameModel.getCountry().forEach(countryModel -> {
            this.webSocketService.sendUpdateCountry(countryModel.getId());
        });
        Step step = Step.START;
        do {
            discordService.closeAllRequestByStepAndGameId(step, idGame);
            step = step.next();
        }
        while ((!gameModel.getCurrentStep().equals(Step.START)) && !step.equals(gameModel.getCurrentStep()));
    }
}
