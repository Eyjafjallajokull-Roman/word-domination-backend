package com.mpls.mainservice.dto.command;

public class UserCommandDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;

    public String getLogin() {
        return login;
    }

    public UserCommandDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserCommandDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserCommandDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserCommandDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
