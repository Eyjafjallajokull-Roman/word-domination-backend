package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.dto.CityIdDto;
import com.mpls.mainservice.dto.create.ShieldCreateDto;
import com.mpls.mainservice.model.CityModel;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.SanctionModel;
import com.mpls.mainservice.model.ShieldModel;
import com.mpls.mainservice.repository.CityRepository;
import com.mpls.mainservice.repository.ShieldRepository;
import com.mpls.mainservice.service.CalculationService;
import com.mpls.mainservice.service.CityService;
import com.mpls.mainservice.service.ShieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShieldServiceImpl implements ShieldService {
    @Autowired
    private ShieldRepository shieldRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CalculationService calculationService;

    @Override
    public List<ShieldModel> findAll() {
        return shieldRepository.findAll();
    }

    @Override
    public List<ShieldModel> create(List<ShieldCreateDto> list, DecisionModel model) {
        //todo add validation
        return list.stream().map(shieldCreateDto ->
                save(new ShieldModel().setDecision(model)
                        .setCity(cityService.findOne(shieldCreateDto.getCity().getId()))
                        .setRuined(false))
        ).collect(Collectors.toList());
    }

    @Override
    public List<ShieldModel> ruinedShield(CityModel cityModel) {
        cityRepository.save(cityModel.setShield(!calculationService.calculationShieldRuined(cityModel)));
        if (cityModel.getShield()) {
            cityModel.setShieldList(cityModel.getShieldList().stream().map(shieldModel -> save(shieldModel.setRuined(true))).collect(Collectors.toList()));
        }
        return cityModel.getShieldList();
    }

    @Override
    public ShieldModel findOne(Long id) {
        return shieldRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        shieldRepository.deleteById(id);
    }

    @Override
    public ShieldModel save(ShieldModel shieldModel) {
        return shieldRepository.save(shieldModel);
    }
}
