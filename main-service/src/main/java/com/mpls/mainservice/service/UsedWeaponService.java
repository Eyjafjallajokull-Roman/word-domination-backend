package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.create.UsedWeaponCreateDto;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.EcologyModel;
import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.model.UsedWeaponModel;
import com.mpls.mainservice.model.enums.Step;

import java.util.List;

public interface UsedWeaponService {

    Long countBySessionCountryGameId(Long sessionCountryGameId);
    Long countByDecisionCountryGameIdAndDecisionStep(Long sessionCountryGameId, Step step);
    List<UsedWeaponModel> findAll();
    UsedWeaponModel findOne(Long id);
    void deleteById(Long id);
    UsedWeaponModel save(UsedWeaponModel usedWeaponModel);

    List<UsedWeaponModel> create(List<UsedWeaponCreateDto> usedWeaponCreateDto, DecisionModel decisionModel);

}
