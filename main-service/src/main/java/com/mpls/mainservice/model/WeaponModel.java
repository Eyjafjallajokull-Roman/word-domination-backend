package com.mpls.mainservice.model;

import javax.persistence.*;

@Entity
public class WeaponModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private DecisionModel decision;

    public Long getId() {
        return id;
    }

    public WeaponModel setId(Long id) {
        this.id = id;
        return this;
    }

    public DecisionModel getDecision() {
        return decision;
    }

    public WeaponModel setDecision(DecisionModel session) {
        this.decision = session;
        return this;
    }
}
