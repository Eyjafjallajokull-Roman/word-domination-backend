package com.mpls.mainservice.model;

import com.mpls.mainservice.model.enums.DiscordRequest;
import com.mpls.mainservice.model.enums.Step;

import javax.persistence.*;
import java.util.List;

@Entity
public class DiscordConnectRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private CountryModel countryTo;
    @OneToOne
    private CountryModel countryFrom;

    @OneToMany(mappedBy = "discordConnectRequest")
    private List<UserDiscordModel> users;


    @Enumerated(EnumType.STRING)
    private DiscordRequest discordRequest;

    private Boolean end;

    private Step step;

    public Step getStep() {
        return step;
    }

    public DiscordConnectRequestModel setStep(Step step) {
        this.step = step;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CountryModel getCountryFrom() {
        return countryFrom;
    }

    public DiscordConnectRequestModel setCountryFrom(CountryModel countryFrom) {
        this.countryFrom = countryFrom;
        return this;
    }

    public DiscordConnectRequestModel setId(Long id) {
        this.id = id;
        return this;
    }

    public CountryModel getCountryTo() {
        return countryTo;
    }

    public DiscordConnectRequestModel setCountryTo(CountryModel countryTo) {
        this.countryTo = countryTo;
        return this;
    }

    public List<UserDiscordModel> getUsers() {
        return users;
    }

    public DiscordConnectRequestModel setUsers(List<UserDiscordModel> users) {
        this.users = users;
        return this;
    }



    public Boolean getEnd() {
        return end;
    }

    public DiscordConnectRequestModel setEnd(Boolean end) {
        this.end = end;
        return this;
    }

    public DiscordRequest getDiscordRequest() {
        return discordRequest;
    }

    public DiscordConnectRequestModel setDiscordRequest(DiscordRequest discordRequest) {
        this.discordRequest = discordRequest;
        return this;
    }
}
