package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.create.DecisionCreateDto;
import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.enums.Step;

import java.security.Principal;
import java.util.List;

public interface DecisionService {

    DecisionModel create(DecisionCreateDto createDto, Principal principal);

    List<DecisionModel> findAll();

    DecisionModel findOne(Long id);

    void deleteById(Long id);

    DecisionModel save(DecisionModel decisionModel);

    Long countByCountryIdAndStep(Long country_id, Step step);

}
