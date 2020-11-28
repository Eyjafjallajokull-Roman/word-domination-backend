package com.mpls.mainservice.dto.create;

import com.mpls.mainservice.dto.CityIdDto;
import com.mpls.mainservice.dto.WeaponIdDto;
import com.mpls.mainservice.model.CityModel;
import com.mpls.mainservice.model.WeaponModel;

import javax.persistence.ManyToOne;

public class UsedWeaponCreateDto {

    private WeaponIdDto weapon;
    private CityIdDto city;

    public WeaponIdDto getWeapon() {
        return weapon;
    }

    public UsedWeaponCreateDto setWeapon(WeaponIdDto weapon) {
        this.weapon = weapon;
        return this;
    }

    public CityIdDto getCity() {
        return city;
    }

    public UsedWeaponCreateDto setCity(CityIdDto city) {
        this.city = city;
        return this;
    }
}
