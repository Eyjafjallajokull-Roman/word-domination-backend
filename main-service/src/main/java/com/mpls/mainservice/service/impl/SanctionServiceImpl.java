package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.dto.create.SanctionCreateDto;
import com.mpls.mainservice.model.CityModel;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.SanctionModel;
import com.mpls.mainservice.repository.SanctionRepository;
import com.mpls.mainservice.service.CountryService;
import com.mpls.mainservice.service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SanctionServiceImpl implements SanctionService {
    @Autowired
    private SanctionRepository sanctionRepository;
    @Autowired
    private CountryService countryService;

    @Override
    public List<SanctionModel> findAll() {
        return sanctionRepository.findAll();
    }

    @Override
    public List<SanctionModel> create(List<SanctionCreateDto> create, DecisionModel decisionModel) {
        return create.stream().map(sanctionCreateDto ->
                save(new SanctionModel()
                        .setCountry(countryService.findOne(sanctionCreateDto.getCountry().getId()))
                        .setDecision(decisionModel))
        ).collect(Collectors.toList());
    }

    @Override
    public SanctionModel findOne(Long id) {
        return sanctionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        sanctionRepository.deleteById(id);
    }

    @Override
    public SanctionModel save(SanctionModel sanctionModel) {
        return sanctionRepository.save(sanctionModel);
    }
}
