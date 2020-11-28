package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.dto.base.UsedWeaponBaseDto;
import com.mpls.mainservice.dto.statistic.GlobalDecisionDto;
import com.mpls.mainservice.dto.statistic.GlobalStatisticDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.model.UsedWeaponModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.DecisionRepository;
import com.mpls.mainservice.repository.GameRepository;
import com.mpls.mainservice.repository.UsedWeaponRepository;
import com.mpls.mainservice.repository.WeaponRepository;
import com.mpls.mainservice.service.GameService;
import com.mpls.mainservice.service.GlobalStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.builder.Builder.map;

@Service
public class GlobalStatisticServiceImpl implements GlobalStatisticService {

    @Autowired
    private UsedWeaponRepository usedWeaponRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private DecisionRepository decisionRepository;

    @Override
    public List<GlobalStatisticDto> parseGlobalStatisticDto(Long idGame) {
        Step step = Step.FIRST;
        GameModel gameModel = gameService.findOne(idGame);
        if (!gameModel.getCurrentStep().next().equals(Step.END)) {
            throw new RuntimeException();
        }
        List<GlobalStatisticDto> globalStatistic = new ArrayList<>();
        do {
            GlobalStatisticDto dto = new GlobalStatisticDto();
            dto.setStep(step);
            Step finalStep = step;
            dto.setGlobalDecisionDto(gameModel.getCountry().stream().map(country -> parseGlobalStatisticDto(finalStep, country)).collect(Collectors.toList()));
            globalStatistic.add(dto);
            step = step.next();
        } while (!step.equals(Step.END));
        return globalStatistic;
    }

    @Override
    public GlobalDecisionDto parseGlobalStatisticDto(Step step, CountryModel country) {
        return map(decisionRepository.findByCountry_IdAndStep(country.getId(), step), GlobalDecisionDto.class)
                .setCreateWeaponCount(weaponRepository.countByDecision_Country_IdAndDecision_Step(country.getId(), step));
    }
}
