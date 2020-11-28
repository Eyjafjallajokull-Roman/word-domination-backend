package com.mpls.mainservice.dto.discord.create;


import com.mpls.mainservice.dto.CountryBaseDto;
import com.mpls.mainservice.dto.CountryIdDto;
import com.mpls.mainservice.dto.command.UserCommandDto;
import com.mpls.mainservice.dto.create.UserCreateDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.enums.GameIdDto;

import java.util.List;

public class DiscordConnectRequestCreateDto {

    private List<UserIdDto> users;
    private CountryIdDto countryTo;
    private GameIdDto game;

    public GameIdDto getGame() {
        return game;
    }

    public DiscordConnectRequestCreateDto setGame(GameIdDto game) {
        this.game = game;
        return this;
    }

    public List<UserIdDto> getUsers() {
        return users;
    }

    public DiscordConnectRequestCreateDto setUsers(List<UserIdDto> users) {
        this.users = users;
        return this;
    }

    public CountryIdDto getCountryTo() {
        return countryTo;
    }

    public DiscordConnectRequestCreateDto setCountryTo(CountryIdDto countryTo) {
        this.countryTo = countryTo;
        return this;
    }
}
