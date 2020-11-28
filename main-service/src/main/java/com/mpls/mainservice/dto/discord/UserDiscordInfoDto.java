package com.mpls.mainservice.dto.discord;

import com.mpls.mainservice.dto.command.UserCommandDto;
import com.mpls.mainservice.model.DiscordConnectRequestModel;
import com.mpls.mainservice.model.UserModel;

import javax.persistence.*;

public class UserDiscordInfoDto {
    private Long id;
    private UserCommandDto user;

    public Long getId() {
        return id;
    }

    public UserDiscordInfoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public UserCommandDto getUser() {
        return user;
    }

    public UserDiscordInfoDto setUser(UserCommandDto user) {
        this.user = user;
        return this;
    }
}
