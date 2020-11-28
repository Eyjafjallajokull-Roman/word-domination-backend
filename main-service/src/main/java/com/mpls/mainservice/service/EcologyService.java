package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.ecology.graphic.EcologyGraphicDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.EcologyModel;
import com.mpls.mainservice.model.enums.Step;

import java.math.BigDecimal;
import java.util.List;

public interface EcologyService {

    List<EcologyGraphicDto> findEcologyGraphic(Long idGame);

    BigDecimal collectEcologyByGame(Long idGame);
    BigDecimal collectEcologyByGame(Long idGame, Step step);

    BigDecimal collectEcology(long countEcology, long countCreateWeapon, long countUsedWeapon);

    List<EcologyModel> findAll();

    EcologyModel findOne(Long id);

    void deleteById(Long id);

    EcologyModel save(EcologyModel ecologyModel);

    EcologyModel create(DecisionModel decisionModel);

}
