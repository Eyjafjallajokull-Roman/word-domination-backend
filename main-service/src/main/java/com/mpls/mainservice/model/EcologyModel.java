package com.mpls.mainservice.model;


import javax.persistence.*;

@Entity
public class EcologyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GameModel game;

    @ManyToOne
    private DecisionModel decision;

    public Long getId() {
        return id;
    }

    public EcologyModel setId(Long id) {
        this.id = id;
        return this;
    }

    public DecisionModel getDecision() {
        return decision;
    }

    public EcologyModel setDecision(DecisionModel decision) {
        this.decision = decision;
        return this;
    }

    public GameModel getGame() {
        return game;
    }

    public EcologyModel setGame(GameModel game) {
        this.game = game;
        return this;
    }
}
