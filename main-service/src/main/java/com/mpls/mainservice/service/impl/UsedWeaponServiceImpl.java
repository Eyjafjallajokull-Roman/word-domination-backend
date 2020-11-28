package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.dto.create.UsedWeaponCreateDto;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.UsedWeaponModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.UsedWeaponRepository;
import com.mpls.mainservice.service.CityService;
import com.mpls.mainservice.service.UsedWeaponService;
import com.mpls.mainservice.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsedWeaponServiceImpl implements UsedWeaponService {

    @Autowired
    private UsedWeaponRepository usedWeaponRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private WeaponService weaponService;

    @Override
    public Long countBySessionCountryGameId(Long sessionCountryGameId) {
        return usedWeaponRepository.countByDecision_Country_Game_Id(sessionCountryGameId);
    }

    @Override
    public Long countByDecisionCountryGameIdAndDecisionStep(Long sessionCountryGameId, Step step) {
        return usedWeaponRepository.countByDecision_Country_Game_IdAndDecision_Step(sessionCountryGameId,step);
    }

    @Override
    public List<UsedWeaponModel> findAll() {
        return usedWeaponRepository.findAll();
    }

    @Override
    public UsedWeaponModel findOne(Long id) {
        return usedWeaponRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        usedWeaponRepository.deleteById(id);
    }

    @Override
    public UsedWeaponModel save(UsedWeaponModel usedWeaponModel) {
        return usedWeaponRepository.save(usedWeaponModel);
    }

    @Override
    public List<UsedWeaponModel> create(List<UsedWeaponCreateDto> usedWeaponCreateDto, DecisionModel decisionModel) {
        return usedWeaponCreateDto.stream().map(ucd ->
                save(new UsedWeaponModel()
                        .setDecision(decisionModel)
                        .setCity(cityService.findOne(ucd.getCity().getId()))
                        .setWeapon(weaponService.findOne(ucd.getWeapon().getId())))
        ).collect(Collectors.toList());
    }
}
