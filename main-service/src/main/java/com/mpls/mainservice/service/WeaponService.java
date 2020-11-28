package com.mpls.mainservice.service;

import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.EcologyModel;
import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.model.WeaponModel;
import com.mpls.mainservice.model.enums.Step;

import java.util.List;

public interface WeaponService {
    Long countBySessionCountryGameId(Long idCountry);

    Long countByDecisionCountryGameIdAndDecisionStep(Long idCountry, Step step);

    List<WeaponModel> findAll();

    WeaponModel findOne(Long id);

    void deleteById(Long id);

    WeaponModel save(WeaponModel weaponModel);

    List<WeaponModel> create(Long count, DecisionModel decisionModel);

    List<WeaponModel> findALlFreeWeapon(Long idCountry);
}
