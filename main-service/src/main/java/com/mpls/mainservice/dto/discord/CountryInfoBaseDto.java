package com.mpls.mainservice.dto.discord;

import javax.persistence.Column;

public class CountryInfoBaseDto {
    private Long id;
    private String name;
    private String color;
    private String photo;

    public Long getId() {
        return id;
    }

    public CountryInfoBaseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryInfoBaseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CountryInfoBaseDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CountryInfoBaseDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }
}
