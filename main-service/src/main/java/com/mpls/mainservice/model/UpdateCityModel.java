package com.mpls.mainservice.model;

import javax.persistence.*;

@Entity
public class UpdateCityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private DecisionModel decision;
    @ManyToOne
    private CityModel city;

    public Long getId() {
        return id;
    }

    public UpdateCityModel setId(Long id) {
        this.id = id;
        return this;
    }

    public DecisionModel getDecision() {
        return decision;
    }

    public UpdateCityModel setDecision(DecisionModel session) {
        this.decision = session;
        return this;
    }

    public CityModel getCity() {
        return city;
    }

    public UpdateCityModel setCity(CityModel city) {
        this.city = city;
        return this;
    }
}
