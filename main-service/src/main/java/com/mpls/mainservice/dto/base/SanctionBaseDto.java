package com.mpls.mainservice.dto.base;

import com.mpls.mainservice.dto.CountryBaseDto;
import com.mpls.mainservice.model.CountryModel;

import javax.persistence.ManyToOne;

public class SanctionBaseDto {
    private CountryBaseDto country;

    public CountryBaseDto getCountry() {
        return country;
    }

    public SanctionBaseDto setCountry(CountryBaseDto country) {
        this.country = country;
        return this;
    }


}
