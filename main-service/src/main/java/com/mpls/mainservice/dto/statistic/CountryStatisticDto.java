package com.mpls.mainservice.dto.statistic;

import com.mpls.mainservice.model.CityModel;

import java.math.BigDecimal;
import java.util.List;

public class CountryStatisticDto {
    private Long id;
    private String name;
    private String color;
    private String photo;
    private List<CityStatisticDto> city;
    private Boolean decision;
    private Long point;

    public Long getId() {
        return id;
    }

    public Long getPoint() {
        return point;
    }

    public CountryStatisticDto setPoint(Long point) {
        this.point = point;
        return this;
    }

    public Boolean getDecision() {
        return decision;
    }

    public CountryStatisticDto setDecision(Boolean decision) {
        this.decision = decision;
        return this;
    }

    public CountryStatisticDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryStatisticDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CountryStatisticDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CountryStatisticDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }


    public List<CityStatisticDto> getCity() {
        return city;
    }

    public CountryStatisticDto setCity(List<CityStatisticDto> city) {
        this.city = city;
        return this;
    }
}
