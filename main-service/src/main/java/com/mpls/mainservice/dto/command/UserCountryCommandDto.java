package com.mpls.mainservice.dto.command;

import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.UserModel;

import javax.persistence.ManyToOne;

public class UserCountryCommandDto {
    private Long id;
    private UserCommandDto user;

    public Long getId() {
        return id;
    }

    public UserCountryCommandDto setId(Long id) {
        this.id = id;
        return this;
    }

    public UserCommandDto getUser() {
        return user;
    }

    public UserCountryCommandDto setUser(UserCommandDto user) {
        this.user = user;
        return this;
    }
}
