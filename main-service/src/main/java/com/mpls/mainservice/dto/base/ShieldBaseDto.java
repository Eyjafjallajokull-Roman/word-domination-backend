package com.mpls.mainservice.dto.base;

import com.mpls.mainservice.model.CityModel;

import javax.persistence.ManyToOne;

public class ShieldBaseDto {


    private CityBaseDto city;

    public CityBaseDto getCity() {
        return city;
    }

    public ShieldBaseDto setCity(CityBaseDto city) {
        this.city = city;
        return this;
    }


}
