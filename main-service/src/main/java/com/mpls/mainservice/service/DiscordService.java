package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.discord.DiscordConnectRequestInfoDto;
import com.mpls.mainservice.dto.discord.create.DiscordConnectRequestCreateDto;
import com.mpls.mainservice.model.DiscordConnectRequestModel;
import com.mpls.mainservice.model.enums.Step;

import java.security.Principal;
import java.util.List;

public interface DiscordService {


    List<DiscordConnectRequestInfoDto> findMyRequest(Principal principal,Long idGame);

    List<DiscordConnectRequestInfoDto> findRequestToMyCountry(Principal principal,Long idGame);

    DiscordConnectRequestInfoDto create(DiscordConnectRequestCreateDto connectRequestCreate,Principal principal);

    DiscordConnectRequestModel confirm(Long idDiscordConnectRequest);

    DiscordConnectRequestModel reject(Long idDiscordConnectRequest);

    DiscordConnectRequestModel end(Long idDiscordConnectRequest);

    void allGoInGlobal(Long idGame);

    void allCountryGoHome(Long idGame);

    void closeAllRequestByStepAndGameId(Step step,Long idGame);
}
