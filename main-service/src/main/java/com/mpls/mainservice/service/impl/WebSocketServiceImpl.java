package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.config.ws.WSPattern;
import com.mpls.mainservice.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceImpl implements WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void sendNewGame() {
        template.convertAndSend(WSPattern.NEW_GAME, "");
    }

    @Override
    public void sendUpdateCountry(Long idCountry) {
        template.convertAndSend(WSPattern.UPDATE_COUNTRY + idCountry, "");
    }

    @Override
    public void sendUpdateGame(Long idCountry) {
        template.convertAndSend(WSPattern.UPDATE_GAME + idCountry, "");
    }

    @Override
    public void sendUpdateMoney(Long idCountry) {
        template.convertAndSend(WSPattern.UPDATE_MONEY + idCountry, "");
    }

    @Override
    public void sendUpdateUserCountry(Long idCountry) {
        template.convertAndSend(WSPattern.UPDATE_USER_COUNTRY + idCountry, "");
    }

    @Override
    public void sendUpdateDiscordRequestFrom(Long idCountry) {
        template.convertAndSend(WSPattern.UPDATE_DISCORD_REQUEST_FROM + idCountry, "");
    }

    @Override
    public void sendUpdateDiscordRequestTo(Long idCountry) {
        template.convertAndSend(WSPattern.UPDATE_DISCORD_REQUEST_TO + idCountry, "");
    }

    @Override
    public void sendUpdateUserGame(Long idGame) {
        template.convertAndSend(WSPattern.UPDATE_USER_GAME + idGame, "");
    }

    @Override
    public void sendReloadFreeGame() {
        template.convertAndSend(WSPattern.RELOAD_FREE_GAME, "");
    }

    @Override
    public void sendReloadGame() {
        template.convertAndSend(WSPattern.RELOAD_GAME, "");
    }
}
