package com.mpls.mainservice.dto.info;

import com.mpls.mainservice.model.CityModel;

import java.util.List;

public class CountryInfoDto {
    private Long id;
    private String name;
    private String color;
    private String photo;
    private List<CityInfoDto> city;

    public Long getId() {
        return id;
    }

    public CountryInfoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryInfoDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CountryInfoDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CountryInfoDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public List<CityInfoDto> getCity() {
        return city;
    }

    public CountryInfoDto setCity(List<CityInfoDto> city) {
        this.city = city;
        return this;
    }
}
