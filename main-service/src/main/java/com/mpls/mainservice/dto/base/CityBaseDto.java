package com.mpls.mainservice.dto.base;

public class CityBaseDto {
    private Long id;
    private String name;
    private String color;
    private String photo;
    private Boolean shield;
    private Boolean ruined;

    public Long getId() {
        return id;
    }

    public CityBaseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityBaseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CityBaseDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CityBaseDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Boolean getShield() {
        return shield;
    }

    public CityBaseDto setShield(Boolean shield) {
        this.shield = shield;
        return this;
    }

    public Boolean getRuined() {
        return ruined;
    }

    public CityBaseDto setRuined(Boolean ruined) {
        this.ruined = ruined;
        return this;
    }
}
