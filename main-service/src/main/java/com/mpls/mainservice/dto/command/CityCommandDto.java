package com.mpls.mainservice.dto.command;

import java.math.BigDecimal;
import java.util.List;

public class CityCommandDto {
    private Long id;
    private String name;
    private String color;
    private String photo;
    private Boolean shield;
    private Boolean ruined;
    private BigDecimal economy;
    private List<UpdateCityCommandDto> updateCity;

    public Long getId() {
        return id;
    }

    public CityCommandDto setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getEconomy() {
        return economy;
    }

    public CityCommandDto setEconomy(BigDecimal economy) {
        this.economy = economy;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityCommandDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CityCommandDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CityCommandDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Boolean getShield() {
        return shield;
    }

    public CityCommandDto setShield(Boolean shield) {
        this.shield = shield;
        return this;
    }

    public Boolean getRuined() {
        return ruined;
    }

    public CityCommandDto setRuined(Boolean ruined) {
        this.ruined = ruined;
        return this;
    }

    public List<UpdateCityCommandDto> getUpdateCity() {
        return updateCity;
    }

    public CityCommandDto setUpdateCity(List<UpdateCityCommandDto> updateCity) {
        this.updateCity = updateCity;
        return this;
    }
}
