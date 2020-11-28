package com.mpls.mainservice.model;

import com.mpls.mainservice.model.enums.Step;

import javax.persistence.*;
import java.util.List;

@Entity
public class GameModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "game")
    private List<CountryModel> country;

    @Enumerated(EnumType.STRING)
    private Step currentStep;

    @OneToMany(mappedBy = "game")
    private List<EcologyModel> ecology;

    public Long getId() {
        return id;
    }

    public GameModel setId(Long id) {
        this.id = id;
        return this;
    }

    public List<CountryModel> getCountry() {
        return country;
    }

    public GameModel setCountry(List<CountryModel> country) {
        this.country = country;
        return this;
    }

    public Step getCurrentStep() {
        return currentStep;
    }

    public GameModel setCurrentStep(Step relevantNumber) {
        this.currentStep = relevantNumber;
        return this;
    }

    public List<EcologyModel> getEcology() {
        return ecology;
    }

    public GameModel setEcology(List<EcologyModel> ecology) {
        this.ecology = ecology;
        return this;
    }
}
