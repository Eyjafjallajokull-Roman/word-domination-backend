package com.mpls.mainservice.model;

import com.mpls.mainservice.model.enums.Step;

import javax.persistence.*;
import java.util.List;

@Entity
public class CityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    @Column(columnDefinition = "TEXT")
    private String photo;
    private Boolean shield;
    @ManyToOne
    private CountryModel country;
    private Boolean ruined;
    @Enumerated(value = EnumType.STRING)
    private Step stepRuined;
    @OneToMany(mappedBy = "city")
    private List<UpdateCityModel> updateCity;
    @OneToMany(mappedBy = "city")
    private List<UsedWeaponModel> usedWeapon;
    @OneToMany(mappedBy = "city")
    private List<ShieldModel> shieldList;


    public Long getId() {
        return id;
    }

    public CityModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Step getStepRuined() {
        return stepRuined;
    }

    public CityModel setStepRuined(Step stepRuined) {
        this.stepRuined = stepRuined;
        return this;
    }

    public List<ShieldModel> getShieldList() {
        return shieldList;
    }

    public CityModel setShieldList(List<ShieldModel> shieldList) {
        this.shieldList = shieldList;
        return this;
    }

    public Boolean getRuined() {
        return ruined;
    }

    public CityModel setRuined(Boolean ruined) {
        this.ruined = ruined;
        return this;
    }

    public Boolean getShield() {
        return shield;
    }

    public CityModel setShield(Boolean shield) {
        this.shield = shield;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CityModel setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CityModel setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public CountryModel getCountry() {
        return country;
    }

    public CityModel setCountry(CountryModel country) {
        this.country = country;
        return this;
    }

    public List<UpdateCityModel> getUpdateCity() {
        return updateCity;
    }

    public CityModel setUpdateCity(List<UpdateCityModel> updateCity) {
        this.updateCity = updateCity;
        return this;
    }

    public List<UsedWeaponModel> getUsedWeapon() {
        return usedWeapon;
    }

    public CityModel setUsedWeapon(List<UsedWeaponModel> usedWeapon) {
        this.usedWeapon = usedWeapon;
        return this;
    }
}
