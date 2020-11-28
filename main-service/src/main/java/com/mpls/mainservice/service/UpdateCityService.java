package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.create.UpdateCityCreateDto;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.UpdateCityModel;

import java.util.List;

public interface UpdateCityService {
    List<UpdateCityModel> findAll();

    UpdateCityModel findOne(Long id);

    void deleteById(Long id);

    UpdateCityModel save(UpdateCityModel updateCityModel);

    List<UpdateCityModel> create(List<UpdateCityCreateDto> create, DecisionModel model);
}
