package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.dto.create.DecisionCreateDto;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.EcologyModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.DecisionRepository;
import com.mpls.mainservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DecisionServiceImpl implements DecisionService {
    @Autowired
    private DecisionRepository decisionRepository;

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private EcologyService ecologyService;

    @Autowired
    private SanctionService sanctionService;

    @Autowired
    private ShieldService shieldService;

    @Autowired
    private UsedWeaponService usedWeaponService;

    @Autowired
    private UpdateCityService updateCityService;

    @Autowired
    private WebSocketService webSocketService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public DecisionModel create(DecisionCreateDto createDto, Principal principal) {
        DecisionModel model = save(
                new DecisionModel()
                        .setCountry(countryService.findOne(createDto.getCountry().getId()))
        );
        model.setCreateWeapon(weaponService.create(createDto.getCountCreateWeapon(), model));
        model = save(model.setCountry(countryService.findByGameIdAndUserLogin(model.getCountry().getGame().getId(), principal)));
        if (createDto.getCreateEcology()) {
            ecologyService.create(model);
        }
        model.setSanctions(sanctionService.create(createDto.getSanctions(), model));
        model.setCreateShield(shieldService.create(createDto.getCreateShield(), model));
        if (createDto.getWeaponTechnology() && !model.getCountry().getWeaponTechnology()) {
            countryService.save(model.getCountry().setWeaponTechnology(true));
            model = save(model.setWeaponTechnology(true));
        } else {
            model = save(model.setWeaponTechnology(false));
        }
        model.setUsedWeapon(usedWeaponService.create(createDto.getUsedWeapon(), model));
        model.setUpdateCity(updateCityService.create(createDto.getUpdateCity(), model));
        save(model.setStep(model.getCountry().getGame().getCurrentStep().next()));
        webSocketService.sendUpdateCountry(model.getCountry().getId());
        webSocketService.sendUpdateGame(model.getCountry().getGame().getId());
        return findOne(model.getId());
    }

    @Override
    public List<DecisionModel> findAll() {
        return decisionRepository.findAll();
    }

    @Override
    public DecisionModel findOne(Long id) {
        return decisionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        decisionRepository.deleteById(id);
    }

    @Override
    public DecisionModel save(DecisionModel decisionModel) {
        return decisionRepository.save(decisionModel);
    }

    @Override
    public Long countByCountryIdAndStep(Long country_id, Step step) {
        return decisionRepository.countByCountry_IdAndStep(country_id, step);
    }
}
