package com.mpls.mainservice.dto;

import com.mpls.mainservice.model.enums.Step;

import java.util.List;

public class GameFullInfo {
    private Long id;
    private Step relevantNumber;
    private Boolean myGame;
    private Long countUser;
    private List<CountryBaseDto> country;

    public Long getCountUser() {
        return countUser;
    }

    public GameFullInfo setCountUser(Long countUser) {
        this.countUser = countUser;
        return this;
    }

    public Boolean getMyGame() {
        return myGame;
    }

    public GameFullInfo setMyGame(Boolean myGame) {
        this.myGame = myGame;
        return this;
    }

    public Long getId() {
        return id;
    }

    public GameFullInfo setId(Long id) {
        this.id = id;
        return this;
    }

    public List<CountryBaseDto> getCountry() {
        return country;
    }

    public GameFullInfo setCountry(List<CountryBaseDto> country) {
        this.country = country;
        return this;
    }

    public Step getRelevantNumber() {
        return relevantNumber;
    }

    public GameFullInfo setRelevantNumber(Step relevantNumber) {
        this.relevantNumber = relevantNumber;
        return this;
    }
}
