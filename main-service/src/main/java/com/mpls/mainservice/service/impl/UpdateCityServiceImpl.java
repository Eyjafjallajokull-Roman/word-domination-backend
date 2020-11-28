package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.dto.create.UpdateCityCreateDto;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.UpdateCityModel;
import com.mpls.mainservice.model.UsedWeaponModel;
import com.mpls.mainservice.repository.UpdateCityRepository;
import com.mpls.mainservice.service.CityService;
import com.mpls.mainservice.service.UpdateCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UpdateCityServiceImpl implements UpdateCityService {
    @Autowired
    private UpdateCityRepository updateCityRepository;

    @Autowired
    private CityService cityService;

    @Override
    public List<UpdateCityModel> findAll() {
        return updateCityRepository.findAll();
    }

    @Override
    public UpdateCityModel findOne(Long id) {
        return updateCityRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        updateCityRepository.deleteById(id);

    }

    @Override
    public UpdateCityModel save(UpdateCityModel updateCityModel) {
        return updateCityRepository.save(updateCityModel);
    }

    @Override
    public List<UpdateCityModel> create(List<UpdateCityCreateDto> create, DecisionModel model) {
        return create.stream().map(ucc ->
                save(new UpdateCityModel()
                        .setCity(cityService.findOne(ucc.getCity().getId()))
                        .setDecision(model))
        ).collect(toList());
    }
}
