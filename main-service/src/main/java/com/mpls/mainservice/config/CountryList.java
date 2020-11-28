package com.mpls.mainservice.config;

import java.util.Map;

public class CountryList {
    private String name;
    private String color;
    private String photo;
    private Map<Long, CityList> cityList;

    public Map<Long, CityList> getCityList() {
        return cityList;
    }

    public CountryList setCityList(Map<Long, CityList> cityList) {
        this.cityList = cityList;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryList setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CountryList setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CountryList setPhoto(String photo) {
        this.photo = photo;
        return this;
    }
}
