package com.mpls.mainservice.dto.command;

import com.mpls.mainservice.dto.create.DecisionCreateDto;
import com.mpls.mainservice.model.CountryModel;

public class SanctionCommandDto {
    private Long id;
    private CountryCommandDto country;
    private DecisionIdBaseCommandDtoDto decision;

    public Long getId() {
        return id;
    }

    public SanctionCommandDto setId(Long id) {
        this.id = id;
        return this;
    }

    public CountryCommandDto getCountry() {
        return country;
    }

    public SanctionCommandDto setCountry(CountryCommandDto country) {
        this.country = country;
        return this;
    }

    public DecisionIdBaseCommandDtoDto getDecision() {
        return decision;
    }

    public SanctionCommandDto setDecision(DecisionIdBaseCommandDtoDto decision) {
        this.decision = decision;
        return this;
    }
}
