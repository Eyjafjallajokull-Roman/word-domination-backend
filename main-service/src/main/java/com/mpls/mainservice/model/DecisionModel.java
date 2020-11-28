package com.mpls.mainservice.model;

import com.mpls.mainservice.model.enums.Step;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class DecisionModel {
    private static final String DECISION_MODEL_MAPPING = "decision";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Step step;

    @ManyToOne
    private CountryModel country;

    @OneToMany(mappedBy = DECISION_MODEL_MAPPING)
    private List<UpdateCityModel> updateCity;
    private Long startBudget;
    private Long endBudget;
    private Boolean weaponTechnology;

    @OneToMany(mappedBy = DECISION_MODEL_MAPPING)
    private List<WeaponModel> createWeapon;
    @OneToMany(mappedBy = DECISION_MODEL_MAPPING)
    private List<UsedWeaponModel> usedWeapon;

    @OneToMany(mappedBy = DECISION_MODEL_MAPPING)
    private List<ShieldModel> createShield;

    @OneToMany(mappedBy = DECISION_MODEL_MAPPING)
    private List<SanctionModel> sanctions;

    @OneToMany(mappedBy = DECISION_MODEL_MAPPING)
    private List<EcologyModel> ecology;


    public Long getId() {
        return id;
    }

    public DecisionModel setId(Long id) {
        this.id = id;
        return this;
    }

    public List<ShieldModel> getCreateShield() {
        return createShield;
    }

    public DecisionModel setCreateShield(List<ShieldModel> createShield) {
        this.createShield = createShield;
        return this;
    }

    public List<EcologyModel> getEcology() {
        return ecology;
    }

    public DecisionModel setEcology(List<EcologyModel> ecology) {
        this.ecology = ecology;
        return this;
    }

    public Boolean getWeaponTechnology() {
        return weaponTechnology;
    }

    public DecisionModel setWeaponTechnology(Boolean weaponTechnology) {
        this.weaponTechnology = weaponTechnology;
        return this;
    }

    public Step getStep() {
        return step;
    }

    public DecisionModel setStep(Step step) {
        this.step = step;
        return this;
    }

    public CountryModel getCountry() {
        return country;
    }

    public DecisionModel setCountry(CountryModel country) {
        this.country = country;
        return this;
    }

    public List<UpdateCityModel> getUpdateCity() {
        return updateCity;
    }

    public DecisionModel setUpdateCity(List<UpdateCityModel> updateCity) {
        this.updateCity = updateCity;
        return this;
    }

    public Long getStartBudget() {
        return startBudget;
    }

    public DecisionModel setStartBudget(Long startBudget) {
        this.startBudget = startBudget;
        return this;
    }

    public Long getEndBudget() {
        return endBudget;
    }

    public DecisionModel setEndBudget(Long endBudget) {
        this.endBudget = endBudget;
        return this;
    }

    public List<WeaponModel> getCreateWeapon() {
        return createWeapon;
    }

    public DecisionModel setCreateWeapon(List<WeaponModel> createWeapon) {
        this.createWeapon = createWeapon;
        return this;
    }

    public List<UsedWeaponModel> getUsedWeapon() {
        return usedWeapon;
    }

    public DecisionModel setUsedWeapon(List<UsedWeaponModel> usedWeapon) {
        this.usedWeapon = usedWeapon;
        return this;
    }

    public List<SanctionModel> getSanctions() {
        return sanctions;
    }

    public DecisionModel setSanctions(List<SanctionModel> sanctions) {
        this.sanctions = sanctions;
        return this;
    }
}
