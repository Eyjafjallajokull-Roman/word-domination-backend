package com.mpls.mainservice.config;

public class CityList {
    private String name;
    private String color;
    private String photo;

    public String getName() {
        return name;
    }

    public CityList setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CityList setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CityList setPhoto(String photo) {
        this.photo = photo;
        return this;
    }
}
