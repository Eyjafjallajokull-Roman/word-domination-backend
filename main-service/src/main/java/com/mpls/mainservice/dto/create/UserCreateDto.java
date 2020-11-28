package com.mpls.mainservice.dto.create;

public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String password;
    private String login;

    public String getFirstName() {
        return firstName;
    }

    public UserCreateDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserCreateDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserCreateDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserCreateDto setLogin(String login) {
        this.login = login;
        return this;
    }
}
