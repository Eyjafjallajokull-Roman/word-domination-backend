package com.mpls.mainservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class CountryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    @Column(columnDefinition = "TEXT")
    private String photo;
    @OneToMany(mappedBy = "country")
    private List<CityModel> city;
    @ManyToOne
    private GameModel game;
    private Boolean weaponTechnology;
    @OneToMany(mappedBy = "country")
    private List<UserCountryModel> userCountry;

    @OneToMany(mappedBy = "country")
    private List<DecisionModel> decision;

    @OneToMany(mappedBy = "country")
    private List<SanctionModel> sanctions;

    public Long getId() {
        return id;
    }

    public CountryModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getWeaponTechnology() {
        return weaponTechnology;
    }

    public CountryModel setWeaponTechnology(Boolean weaponTechnology) {
        this.weaponTechnology = weaponTechnology;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CountryModel setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CountryModel setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public List<CityModel> getCity() {
        return city;
    }

    public CountryModel setCity(List<CityModel> city) {
        this.city = city;
        return this;
    }

    public GameModel getGame() {
        return game;
    }

    public CountryModel setGame(GameModel game) {
        this.game = game;
        return this;
    }

    public List<UserCountryModel> getUserCountry() {
        return userCountry;
    }

    public CountryModel setUserCountry(List<UserCountryModel> userCountry) {
        this.userCountry = userCountry;
        return this;
    }

    public List<DecisionModel> getDecision() {
        return decision;
    }

    public CountryModel setDecision(List<DecisionModel> decision) {
        this.decision = decision;
        return this;
    }

    public List<SanctionModel> getSanctions() {
        return sanctions;
    }

    public CountryModel setSanctions(List<SanctionModel> sanctions) {
        this.sanctions = sanctions;
        return this;
    }
}
