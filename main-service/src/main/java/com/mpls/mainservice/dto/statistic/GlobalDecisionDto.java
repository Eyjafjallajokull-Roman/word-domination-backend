package com.mpls.mainservice.dto.statistic;

import com.mpls.mainservice.dto.CountryBaseDto;
import com.mpls.mainservice.dto.base.*;


import java.util.List;

public class GlobalDecisionDto {
    private CountryBaseDto country;
    private Boolean weaponTechnology;
    private List<ShieldBaseDto> createShield;
    private List<SanctionBaseDto> sanctions;
    private List<UpdateCityBaseDto> updateCity;
    private List<EcologyBaseDto> ecology;
    private List<UsedWeaponBaseDto> usedWeapon;
    private Long createWeaponCount;

    public CountryBaseDto getCountry() {
        return country;
    }

    public GlobalDecisionDto setCountry(CountryBaseDto country) {
        this.country = country;
        return this;
    }

    public Boolean getWeaponTechnology() {
        return weaponTechnology;
    }

    public GlobalDecisionDto setWeaponTechnology(Boolean weaponTechnology) {
        this.weaponTechnology = weaponTechnology;
        return this;
    }

    public List<ShieldBaseDto> getCreateShield() {
        return createShield;
    }

    public GlobalDecisionDto setCreateShield(List<ShieldBaseDto> createShield) {
        this.createShield = createShield;
        return this;
    }

    public List<SanctionBaseDto> getSanctions() {
        return sanctions;
    }

    public GlobalDecisionDto setSanctions(List<SanctionBaseDto> sanctions) {
        this.sanctions = sanctions;
        return this;
    }

    public List<UpdateCityBaseDto> getUpdateCity() {
        return updateCity;
    }

    public GlobalDecisionDto setUpdateCity(List<UpdateCityBaseDto> updateCity) {
        this.updateCity = updateCity;
        return this;
    }

    public List<EcologyBaseDto> getEcology() {
        return ecology;
    }

    public GlobalDecisionDto setEcology(List<EcologyBaseDto> ecology) {
        this.ecology = ecology;
        return this;
    }

    public List<UsedWeaponBaseDto> getUsedWeapon() {
        return usedWeapon;
    }

    public GlobalDecisionDto setUsedWeapon(List<UsedWeaponBaseDto> usedWeapon) {
        this.usedWeapon = usedWeapon;
        return this;
    }

    public Long getCreateWeaponCount() {
        return createWeaponCount;
    }

    public GlobalDecisionDto setCreateWeaponCount(Long createWeaponCount) {
        this.createWeaponCount = createWeaponCount;
        return this;
    }
}
