package com.mpls.mainservice.model;

import javax.persistence.*;

@Entity
public class ShieldModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private DecisionModel decision;
    @ManyToOne
    private CityModel city;
    private Boolean ruined;

    public Long getId() {
        return id;
    }

    public ShieldModel setId(Long id) {
        this.id = id;
        return this;
    }

    public DecisionModel getDecision() {
        return decision;
    }

    public ShieldModel setDecision(DecisionModel session) {
        this.decision = session;
        return this;
    }

    public CityModel getCity() {
        return city;
    }

    public ShieldModel setCity(CityModel city) {
        this.city = city;
        return this;
    }

    public Boolean getRuined() {
        return ruined;
    }

    public ShieldModel setRuined(Boolean ruined) {
        this.ruined = ruined;
        return this;
    }
}
