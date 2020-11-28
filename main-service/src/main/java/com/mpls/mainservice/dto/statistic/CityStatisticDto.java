package com.mpls.mainservice.dto.statistic;

import java.math.BigDecimal;

public class CityStatisticDto {
    private Long id;
    private String name;
    private String color;
    private String photo;
    private Boolean ruined;
    private BigDecimal point;
    private BigDecimal economy;

    public Long getId() {
        return id;
    }

    public CityStatisticDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityStatisticDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CityStatisticDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CityStatisticDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Boolean getRuined() {
        return ruined;
    }

    public CityStatisticDto setRuined(Boolean ruined) {
        this.ruined = ruined;
        return this;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public CityStatisticDto setPoint(BigDecimal point) {
        this.point = point;
        return this;
    }

    public BigDecimal getEconomy() {
        return economy;
    }

    public CityStatisticDto setEconomy(BigDecimal economy) {
        this.economy = economy;
        return this;
    }
}
