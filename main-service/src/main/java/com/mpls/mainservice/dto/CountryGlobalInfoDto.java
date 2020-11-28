package com.mpls.mainservice.dto;

import java.math.BigDecimal;
import java.util.List;

public class CountryGlobalInfoDto {
    private String name;
    private String color;
    private String photo;

    private BigDecimal point;

    private List<CityGlobalInfoDto> city;

    public String getName() {
        return name;
    }

    public CountryGlobalInfoDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CountryGlobalInfoDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CountryGlobalInfoDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public List<CityGlobalInfoDto> getCity() {
        return city;
    }

    public CountryGlobalInfoDto setCity(List<CityGlobalInfoDto> city) {
        this.city = city;
        return this;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public CountryGlobalInfoDto setPoint(BigDecimal point) {
        this.point = point;
        return this;
    }
}
