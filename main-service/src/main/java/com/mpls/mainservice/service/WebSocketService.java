package com.mpls.mainservice.service;

public interface WebSocketService {

    void sendNewGame();

    void sendUpdateCountry(Long idCountry);

    void sendUpdateGame(Long idCountry);

    void sendUpdateMoney(Long idCountry);

    void sendUpdateUserCountry(Long idCountry);

    void sendUpdateDiscordRequestFrom(Long idCountry);

    void sendUpdateDiscordRequestTo(Long idCountry);

    void sendUpdateUserGame(Long idGame);

    void sendReloadFreeGame();

    void sendReloadGame();
}
