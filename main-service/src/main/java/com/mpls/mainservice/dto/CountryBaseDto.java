package com.mpls.mainservice.dto;

import com.mpls.mainservice.dto.command.UserCountryCommandDto;
import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.model.UserCountryModel;

import java.util.List;

public class CountryBaseDto {
    private Long id;
    private String name;
    private String color;
    private String photo;
    private Boolean weaponTechnology;
    private Boolean myGame;
    private List<UserCountryCommandDto> userCountry;

    public List<UserCountryCommandDto> getUserCountry() {
        return userCountry;
    }

    public CountryBaseDto setUserCountry(List<UserCountryCommandDto> userCountry) {
        this.userCountry = userCountry;
        return this;
    }

    public Boolean getMyGame() {
        return myGame;
    }

    public CountryBaseDto setMyGame(Boolean myGame) {
        this.myGame = myGame;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CountryBaseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryBaseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CountryBaseDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CountryBaseDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Boolean getWeaponTechnology() {
        return weaponTechnology;
    }

    public CountryBaseDto setWeaponTechnology(Boolean weaponTechnology) {
        this.weaponTechnology = weaponTechnology;
        return this;
    }
}
