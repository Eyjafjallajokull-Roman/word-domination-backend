package com.mpls.mainservice.model;

import javax.persistence.*;

@Entity
public class UserCountryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CountryModel country;
    @ManyToOne
    private UserModel user;
    @OneToOne
    private GameModel game;

    public Long getId() {
        return id;
    }

    public UserCountryModel setId(Long id) {
        this.id = id;
        return this;
    }

    public CountryModel getCountry() {
        return country;
    }

    public UserCountryModel setCountry(CountryModel country) {
        this.country = country;
        return this;
    }

    public UserModel getUser() {
        return user;
    }

    public UserCountryModel setUser(UserModel user) {
        this.user = user;
        return this;
    }

    public GameModel getGame() {
        return game;
    }

    public UserCountryModel setGame(GameModel game) {
        this.game = game;
        return this;
    }
}
