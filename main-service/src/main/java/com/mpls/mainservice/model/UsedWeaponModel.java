package com.mpls.mainservice.model;

import javax.persistence.*;

@Entity
public class UsedWeaponModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private WeaponModel weapon;
    @ManyToOne
    private CityModel city;

    @ManyToOne
    private DecisionModel decision;

    public Long getId() {
        return id;
    }

    public UsedWeaponModel setId(Long id) {
        this.id = id;
        return this;
    }

    public WeaponModel getWeapon() {
        return weapon;
    }

    public UsedWeaponModel setWeapon(WeaponModel weapon) {
        this.weapon = weapon;
        return this;
    }

    public CityModel getCity() {
        return city;
    }

    public UsedWeaponModel setCity(CityModel city) {
        this.city = city;
        return this;
    }

    public DecisionModel getDecision() {
        return decision;
    }

    public UsedWeaponModel setDecision(DecisionModel session) {
        this.decision = session;
        return this;
    }
}
