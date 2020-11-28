package com.mpls.mainservice.dto.command;

import com.mpls.mainservice.dto.WeaponIdDto;
import com.mpls.mainservice.model.*;
import com.mpls.mainservice.model.enums.Step;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

public class CountryFullInfoCommandDto {
    private Long id;
    private String name;
    private String color;
    private String photo;
    private List<CityCommandDto> city;
    private Boolean weaponTechnology;
    private List<UserCountryCommandDto> userCountry;
    private List<SanctionCommandDto> sanctions;
    private Long numberOfWeapon;
    private List<WeaponIdDto> weapon;
    private Long ecology;
    private BigDecimal economy;
    private Step step;
    private Boolean sendDecision;
    public Step getStep() {
        return step;
    }

    public CountryFullInfoCommandDto setStep(Step step) {
        this.step = step;
        return this;
    }

    public Boolean getSendDecision() {
        return sendDecision;
    }

    public CountryFullInfoCommandDto setSendDecision(Boolean sendDecision) {
        this.sendDecision = sendDecision;
        return this;
    }

    public Long getEcology() {
        return ecology;
    }

    public CountryFullInfoCommandDto setEcology(Long ecology) {
        this.ecology = ecology;
        return this;
    }

    public List<WeaponIdDto> getWeapon() {
        return weapon;
    }

    public CountryFullInfoCommandDto setWeapon(List<WeaponIdDto> weapon) {
        this.weapon = weapon;
        return this;
    }

    public Long getNumberOfWeapon() {
        return numberOfWeapon;
    }

    public CountryFullInfoCommandDto setNumberOfWeapon(Long numberOfWeapon) {
        this.numberOfWeapon = numberOfWeapon;
        return this;
    }

    public BigDecimal getEconomy() {
        return economy;
    }

    public CountryFullInfoCommandDto setEconomy(BigDecimal economy) {
        this.economy = economy;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CountryFullInfoCommandDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryFullInfoCommandDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CountryFullInfoCommandDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CountryFullInfoCommandDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public List<CityCommandDto> getCity() {
        return city;
    }

    public CountryFullInfoCommandDto setCity(List<CityCommandDto> city) {
        this.city = city;
        return this;
    }

    public Boolean getWeaponTechnology() {
        return weaponTechnology;
    }

    public CountryFullInfoCommandDto setWeaponTechnology(Boolean weaponTechnology) {
        this.weaponTechnology = weaponTechnology;
        return this;
    }

    public List<UserCountryCommandDto> getUserCountry() {
        return userCountry;
    }

    public CountryFullInfoCommandDto setUserCountry(List<UserCountryCommandDto> userCountry) {
        this.userCountry = userCountry;
        return this;
    }

    public List<SanctionCommandDto> getSanctions() {
        return sanctions;
    }

    public CountryFullInfoCommandDto setSanctions(List<SanctionCommandDto> sanctions) {
        this.sanctions = sanctions;
        return this;
    }
}
