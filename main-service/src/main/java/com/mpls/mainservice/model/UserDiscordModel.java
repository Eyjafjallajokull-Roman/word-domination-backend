package com.mpls.mainservice.model;

import javax.persistence.*;

@Entity
public class UserDiscordModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private DiscordConnectRequestModel discordConnectRequest;
    @OneToOne
    private UserModel user;

    public Long getId() {
        return id;
    }

    public UserDiscordModel setId(Long id) {
        this.id = id;
        return this;
    }

    public DiscordConnectRequestModel getDiscordConnectRequest() {
        return discordConnectRequest;
    }

    public UserDiscordModel setDiscordConnectRequest(DiscordConnectRequestModel discordConnectRequest) {
        this.discordConnectRequest = discordConnectRequest;
        return this;
    }

    public UserModel getUser() {
        return user;
    }

    public UserDiscordModel setUser(UserModel user) {
        this.user = user;
        return this;
    }
}
