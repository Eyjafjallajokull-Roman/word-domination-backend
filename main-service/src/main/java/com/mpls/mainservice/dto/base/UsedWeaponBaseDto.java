package com.mpls.mainservice.dto.base;

import com.mpls.mainservice.model.CityModel;

import javax.persistence.ManyToOne;

public class UsedWeaponBaseDto {

    private CityBaseDto city;

    public CityBaseDto getCity() {
        return city;
    }

    public UsedWeaponBaseDto setCity(CityBaseDto city) {
        this.city = city;
        return this;
    }


}
