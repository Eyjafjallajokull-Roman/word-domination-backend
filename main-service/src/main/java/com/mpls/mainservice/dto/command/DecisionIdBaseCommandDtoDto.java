package com.mpls.mainservice.dto.command;

import com.mpls.mainservice.dto.CountryIdDto;

public class DecisionIdBaseCommandDtoDto {
    private CountryIdDto country;

    public CountryIdDto getCountry() {
        return country;
    }

    public DecisionIdBaseCommandDtoDto setCountry(CountryIdDto country) {
        this.country = country;
        return this;
    }
}
