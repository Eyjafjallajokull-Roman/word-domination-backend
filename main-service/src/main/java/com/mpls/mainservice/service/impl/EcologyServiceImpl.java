package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.config.ConfigGame;
import com.mpls.mainservice.dto.ecology.graphic.EcologyGraphicDto;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.EcologyModel;
import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.DecisionRepository;
import com.mpls.mainservice.repository.EcologyRepository;
import com.mpls.mainservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class EcologyServiceImpl implements EcologyService {

    @Autowired
    private EcologyRepository ecologyRepository;

    @Autowired
    private ConfigGame configGame;
    @Autowired
    private WeaponService weaponService;
    @Autowired
    private UsedWeaponService usedWeaponService;

    @Autowired
    private GameService gameService;

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private DecisionRepository decisionRepository;

    @Override
    public List<EcologyGraphicDto> findEcologyGraphic(Long idGame) {
        Step sn = Step.START;
        GameModel gameModel = gameService.findOne(idGame);
        List<EcologyGraphicDto> list = new ArrayList<>();
        do {
            list.add(
                    new EcologyGraphicDto()
                            .setValue(calculationService.collectEcology(idGame, sn).longValue())
                            .setName(sn.sequenceNumber())
            );
            sn = sn.next();
        } while ((!sn.equals(gameModel.getCurrentStep().next())) && !gameModel.getCurrentStep().equals(Step.START));
        return list;
    }

    @Override
    public BigDecimal collectEcologyByGame(Long idGame) {
        return collectEcologyByGame(idGame, gameService.findOne(idGame).getCurrentStep());
    }

    @Override
    public BigDecimal collectEcologyByGame(Long idGame, Step step) {
        Long countEcology = 0L;
        Long countCreateWeapon = 0L;
        Long countUsedWeapon = 0L;

        Step sn = Step.START;
        do {
            countEcology += ecologyRepository.countByGame_IdAndDecision_Step(idGame, sn);
            countCreateWeapon += weaponService.countByDecisionCountryGameIdAndDecisionStep(idGame, sn);
            countUsedWeapon += usedWeaponService.countByDecisionCountryGameIdAndDecisionStep(idGame, sn);
            countCreateWeapon += decisionRepository.countByCountry_Game_IdAndStepAndWeaponTechnology(idGame, sn, true);
            sn = sn.next();
        } while ((!sn.equals(step.next())) && !step.equals(Step.START));
        return collectEcology(countEcology, countCreateWeapon, countUsedWeapon);
    }

    @Override
    public BigDecimal collectEcology(long countEcology, long countCreateWeapon, long countUsedWeapon) {
        BigDecimal prosForTheEnvironment = configGame.getBaseEcology().subtract(
                BigDecimal.valueOf(countCreateWeapon).multiply(configGame.getFallingEcologyPerCreateWeapon()).add(
                        BigDecimal.valueOf(countUsedWeapon).multiply(configGame.getFallingEcologyPerUsedWeapon())
                )
        ).add(BigDecimal.valueOf(countEcology).multiply(configGame.getGrowthOfEcology())).setScale(0, RoundingMode.UP);
        return prosForTheEnvironment.min(configGame.getMaxEcology()).max(configGame.getMinValueEcology());
    }

    @Override
    public List<EcologyModel> findAll() {
        return new ArrayList<>(ecologyRepository.findAll());
    }

    @Override
    public EcologyModel findOne(Long id) {
        return ecologyRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        ecologyRepository.deleteById(id);
    }

    @Override
    public EcologyModel save(EcologyModel ecologyModel) {
        return ecologyRepository.save(ecologyModel);
    }

    @Override
    public EcologyModel create(DecisionModel decisionModel) {
        return save(new EcologyModel().setDecision(decisionModel).setGame(decisionModel.getCountry().getGame()));
    }
}
