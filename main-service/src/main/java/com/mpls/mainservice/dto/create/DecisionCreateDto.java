package com.mpls.mainservice.dto.create;

import com.mpls.mainservice.dto.CountryIdDto;
import com.mpls.mainservice.model.*;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.validator.annotation.DecisionCreateValidator;

import javax.persistence.*;
import java.util.List;

@DecisionCreateValidator
public class DecisionCreateDto {

    private List<UpdateCityCreateDto> updateCity;
    private Boolean weaponTechnology;
    private Long countCreateWeapon;
    private CountryIdDto country;

    private List<UsedWeaponCreateDto> usedWeapon;

    private List<ShieldCreateDto> createShield;

    private List<SanctionCreateDto> sanctions;

    private Boolean createEcology;

    public CountryIdDto getCountry() {
        return country;
    }

    public DecisionCreateDto setCountry(CountryIdDto country) {
        this.country = country;
        return this;
    }

    public List<UpdateCityCreateDto> getUpdateCity() {
        return updateCity;
    }

    public DecisionCreateDto setUpdateCity(List<UpdateCityCreateDto> updateCity) {
        this.updateCity = updateCity;
        return this;
    }

    public Boolean getWeaponTechnology() {
        return weaponTechnology;
    }

    public DecisionCreateDto setWeaponTechnology(Boolean weaponTechnology) {
        this.weaponTechnology = weaponTechnology;
        return this;
    }

    public Long getCountCreateWeapon() {
        return countCreateWeapon;
    }

    public DecisionCreateDto setCountCreateWeapon(Long countCreateWeapon) {
        this.countCreateWeapon = countCreateWeapon;
        return this;
    }

    public List<UsedWeaponCreateDto> getUsedWeapon() {
        return usedWeapon;
    }

    public DecisionCreateDto setUsedWeapon(List<UsedWeaponCreateDto> usedWeapon) {
        this.usedWeapon = usedWeapon;
        return this;
    }

    public List<ShieldCreateDto> getCreateShield() {
        return createShield;
    }

    public DecisionCreateDto setCreateShield(List<ShieldCreateDto> createShield) {
        this.createShield = createShield;
        return this;
    }

    public List<SanctionCreateDto> getSanctions() {
        return sanctions;
    }

    public DecisionCreateDto setSanctions(List<SanctionCreateDto> sanctions) {
        this.sanctions = sanctions;
        return this;
    }

    public Boolean getCreateEcology() {
        return createEcology;
    }

    public DecisionCreateDto setCreateEcology(Boolean createEcology) {
        this.createEcology = createEcology;
        return this;
    }
}
