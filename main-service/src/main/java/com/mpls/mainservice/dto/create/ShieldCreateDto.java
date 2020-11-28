package com.mpls.mainservice.dto.create;

import com.mpls.mainservice.dto.CityIdDto;
import com.mpls.mainservice.model.CityModel;

public class ShieldCreateDto {
    private CityIdDto city;

    public CityIdDto getCity() {
        return city;
    }

    public ShieldCreateDto setCity(CityIdDto city) {
        this.city = city;
        return this;
    }
}
