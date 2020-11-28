package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.create.SanctionCreateDto;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.SanctionModel;

import java.util.List;

public interface SanctionService {

    List<SanctionModel> create(List<SanctionCreateDto> create, DecisionModel decisionModel);

    List<SanctionModel> findAll();

    SanctionModel findOne(Long id);

    void deleteById(Long id);

    SanctionModel save(SanctionModel sanctionModel);
}
