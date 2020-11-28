package com.mpls.mainservice.dto;

import java.util.List;

public class NominationDto {
    private Long countMaxUseWeapon;
    private List<CountryBaseDto> maxUseWeaponCountry;

    private Long countEcology;
    private List<CountryBaseDto> ecologyCountry;

    private Long countDonate;
    private List<CountryBaseDto> donateCountry;

    private Long countUpdateCity;
    private List<CountryBaseDto> updateCityCountry;

    private Long countMinUseWeapon;
    private List<CountryBaseDto> minUseWeapon;

    public Long getCountMaxUseWeapon() {
        return countMaxUseWeapon;
    }

    public NominationDto setCountMaxUseWeapon(Long countMaxUseWeapon) {
        this.countMaxUseWeapon = countMaxUseWeapon;
        return this;
    }

    public List<CountryBaseDto> getMaxUseWeaponCountry() {
        return maxUseWeaponCountry;
    }

    public NominationDto setMaxUseWeaponCountry(List<CountryBaseDto> maxUseWeaponCountry) {
        this.maxUseWeaponCountry = maxUseWeaponCountry;
        return this;
    }

    public Long getCountEcology() {
        return countEcology;
    }

    public NominationDto setCountEcology(Long countEcology) {
        this.countEcology = countEcology;
        return this;
    }

    public List<CountryBaseDto> getEcologyCountry() {
        return ecologyCountry;
    }

    public NominationDto setEcologyCountry(List<CountryBaseDto> ecologyCountry) {
        this.ecologyCountry = ecologyCountry;
        return this;
    }

    public Long getCountDonate() {
        return countDonate;
    }

    public NominationDto setCountDonate(Long countDonate) {
        this.countDonate = countDonate;
        return this;
    }

    public List<CountryBaseDto> getDonateCountry() {
        return donateCountry;
    }

    public NominationDto setDonateCountry(List<CountryBaseDto> donateCountry) {
        this.donateCountry = donateCountry;
        return this;
    }

    public Long getCountUpdateCity() {
        return countUpdateCity;
    }

    public NominationDto setCountUpdateCity(Long countUpdateCity) {
        this.countUpdateCity = countUpdateCity;
        return this;
    }

    public List<CountryBaseDto> getUpdateCityCountry() {
        return updateCityCountry;
    }

    public NominationDto setUpdateCityCountry(List<CountryBaseDto> updateCityCountry) {
        this.updateCityCountry = updateCityCountry;
        return this;
    }

    public Long getCountMinUseWeapon() {
        return countMinUseWeapon;
    }

    public NominationDto setCountMinUseWeapon(Long countMinUseWeapon) {
        this.countMinUseWeapon = countMinUseWeapon;
        return this;
    }

    public List<CountryBaseDto> getMinUseWeapon() {
        return minUseWeapon;
    }

    public NominationDto setMinUseWeapon(List<CountryBaseDto> minUseWeapon) {
        this.minUseWeapon = minUseWeapon;
        return this;
    }
}
