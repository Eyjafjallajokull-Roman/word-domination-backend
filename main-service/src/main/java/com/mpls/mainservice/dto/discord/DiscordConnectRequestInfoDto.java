package com.mpls.mainservice.dto.discord;

import com.mpls.mainservice.dto.CountryBaseDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.UserDiscordModel;
import com.mpls.mainservice.model.enums.DiscordRequest;
import com.mpls.mainservice.model.enums.Step;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class DiscordConnectRequestInfoDto {

    private Long id;

    private CountryInfoBaseDto countryTo;

    private List<UserDiscordInfoDto> users;


    private Boolean end;

    private DiscordRequest discordRequest;


    private Step step;

    public Step getStep() {
        return step;
    }

    public DiscordConnectRequestInfoDto setStep(Step step) {
        this.step = step;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DiscordConnectRequestInfoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public CountryInfoBaseDto getCountryTo() {
        return countryTo;
    }

    public DiscordConnectRequestInfoDto setCountryTo(CountryInfoBaseDto countryTo) {
        this.countryTo = countryTo;
        return this;
    }

    public List<UserDiscordInfoDto> getUsers() {
        return users;
    }

    public DiscordConnectRequestInfoDto setUsers(List<UserDiscordInfoDto> users) {
        this.users = users;
        return this;
    }

    public DiscordRequest getDiscordRequest() {
        return discordRequest;
    }

    public DiscordConnectRequestInfoDto setDiscordRequest(DiscordRequest discordRequest) {
        this.discordRequest = discordRequest;
        return this;
    }

    public Boolean getEnd() {
        return end;
    }

    public DiscordConnectRequestInfoDto setEnd(Boolean end) {
        this.end = end;
        return this;
    }


}
