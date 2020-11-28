package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.CityIdDto;
import com.mpls.mainservice.dto.create.ShieldCreateDto;
import com.mpls.mainservice.model.CityModel;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.ShieldModel;

import java.util.List;

public interface ShieldService {


    List<ShieldModel> ruinedShield(CityModel cityModel);

    List<ShieldModel> create(List<ShieldCreateDto> list, DecisionModel model);
    List<ShieldModel> findAll();

    ShieldModel findOne(Long id);

    void deleteById(Long id);

    ShieldModel save(ShieldModel shieldModel);
}
