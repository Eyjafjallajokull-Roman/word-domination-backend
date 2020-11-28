package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.WeaponModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.WeaponRepository;
import com.mpls.mainservice.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WeaponServiceImpl implements WeaponService {

    @Autowired
    private WeaponRepository weaponRepository;

    @Override
    public Long countBySessionCountryGameId(Long idCountry) {
        return weaponRepository.countByDecision_Country_Game_Id(idCountry);
    }

    @Override
    public Long countByDecisionCountryGameIdAndDecisionStep(Long idCountry, Step step) {
        return weaponRepository.countByDecision_Country_Game_IdAndDecision_Step(idCountry,step);
    }

    @Override
    public List<WeaponModel> findAll() {
        return weaponRepository.findAll();
    }

    @Override
    public WeaponModel findOne(Long id) {
        return weaponRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        weaponRepository.deleteById(id);

    }

    @Override
    public WeaponModel save(WeaponModel weaponModel) {
        return weaponRepository.save(weaponModel);
    }

    @Override
    public List<WeaponModel> create(Long count, DecisionModel decisionModel) {
        List<WeaponModel> models = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            models.add(
                    save(new WeaponModel()
                            .setDecision(decisionModel))
            );
        }
        return models;
    }

    @Override
    public List<WeaponModel> findALlFreeWeapon(Long idCountry) {
        return weaponRepository.findALlFreeWeapon(idCountry);
    }
}
