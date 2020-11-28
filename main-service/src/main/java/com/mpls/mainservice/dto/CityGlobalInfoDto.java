package com.mpls.mainservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class CityGlobalInfoDto {
    private String name;
    private String color;
    private String photo;
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal economy;
    private Boolean ruined;
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal point;
    public String getName() {
        return name;
    }

    public CityGlobalInfoDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CityGlobalInfoDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CityGlobalInfoDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public BigDecimal getEconomy() {
        return economy;
    }

    public CityGlobalInfoDto setEconomy(BigDecimal economy) {
        this.economy = economy;
        return this;
    }

    public Boolean getRuined() {
        return ruined;
    }

    public CityGlobalInfoDto setRuined(Boolean ruined) {
        this.ruined = ruined;
        return this;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public CityGlobalInfoDto setPoint(BigDecimal point) {
        this.point = point;
        return this;
    }
}
