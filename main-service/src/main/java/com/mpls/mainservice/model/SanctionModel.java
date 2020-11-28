package com.mpls.mainservice.model;

import javax.persistence.*;

@Entity
public class SanctionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DecisionModel decision;
    @ManyToOne
    private CountryModel country;

    public Long getId() {
        return id;
    }

    public SanctionModel setId(Long id) {
        this.id = id;
        return this;
    }

    public DecisionModel getDecision() {
        return decision;
    }

    public SanctionModel setDecision(DecisionModel decision) {
        this.decision = decision;
        return this;
    }

    public CountryModel getCountry() {
        return country;
    }

    public SanctionModel setCountry(CountryModel country) {
        this.country = country;
        return this;
    }
}
