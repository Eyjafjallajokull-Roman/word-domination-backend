package com.mpls.mainservice.dto.info;

import com.mpls.mainservice.model.CountryModel;

import javax.persistence.ManyToOne;

public class CityInfoDto {

    private Long id;
    private String name;
    private String color;
    private String photo;
    private Boolean ruined;


    public Long getId() {
        return id;
    }

    public CityInfoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityInfoDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CityInfoDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CityInfoDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Boolean getRuined() {
        return ruined;
    }

    public CityInfoDto setRuined(Boolean ruined) {
        this.ruined = ruined;
        return this;
    }
}
