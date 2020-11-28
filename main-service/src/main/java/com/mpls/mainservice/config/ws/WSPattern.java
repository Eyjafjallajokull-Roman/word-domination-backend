package com.mpls.mainservice.config.ws;

public interface WSPattern {
    String PING = "/service.ping";
    String NEW_GAME = "/service.new-game";
    String UPDATE_COUNTRY = "/service.update-country/";
    String UPDATE_GAME = "/service.update-game/";
    String UPDATE_MONEY = "/service.update-money/";
    String UPDATE_USER_COUNTRY = "/service.update-user-country/";
    String UPDATE_DISCORD_REQUEST_FROM = "/service.update-discord-request-from/";
    String UPDATE_DISCORD_REQUEST_TO = "/service.update-discord-request-to/";
    String UPDATE_USER_GAME = "/service.update-user-game/";
    String RELOAD_FREE_GAME = "/service.reload-free-game";
    String RELOAD_GAME = "/service.reload-game";
}
