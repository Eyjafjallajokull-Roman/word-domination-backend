package com.mpls.mainservice.dto.create;

import com.mpls.mainservice.dto.CountryIdDto;
import com.mpls.mainservice.model.CountryModel;

public class SanctionCreateDto {
    private CountryIdDto country;

    public CountryIdDto getCountry() {
        return country;
    }

    public SanctionCreateDto setCountry(CountryIdDto country) {
        this.country = country;
        return this;
    }
}
