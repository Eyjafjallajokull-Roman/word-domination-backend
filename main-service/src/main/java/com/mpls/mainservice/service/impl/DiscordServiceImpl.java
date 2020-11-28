package com.mpls.mainservice.service.impl;

import com.mpls.builder.Builder;
import com.mpls.mainservice.config.discord.DiscordAction;
import com.mpls.mainservice.dto.discord.DiscordConnectRequestInfoDto;
import com.mpls.mainservice.dto.discord.create.DiscordConnectRequestCreateDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.DiscordConnectRequestModel;
import com.mpls.mainservice.model.UserCountryModel;
import com.mpls.mainservice.model.UserDiscordModel;
import com.mpls.mainservice.model.enums.DiscordRequest;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.DiscordConnectRequestRepository;
import com.mpls.mainservice.repository.UserDiscordRepository;
import com.mpls.mainservice.repository.UserRepository;
import com.mpls.mainservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscordServiceImpl implements DiscordService {

    private static final String GLOBAL_CHANNEL = "Глобальний";

    @Autowired
    private UserDiscordRepository userDiscordRepository;

    @Autowired
    private DiscordConnectRequestRepository discordConnectRequestRepository;

    @Autowired
    private CountryService countryService;
    @Autowired
    private UserService userService;

    @Autowired
    private DiscordAction discordAction;

    @Autowired
    private GameService gameService;

    @Autowired
    private WebSocketService webSocketService;

    @Override
    public List<DiscordConnectRequestInfoDto> findMyRequest(Principal principal, Long idGame) {
        CountryModel country = countryService.findByGameIdAndUserLogin(idGame, principal);
        return Builder.map(discordConnectRequestRepository.findAllByCountryFrom_Id(country.getId()), DiscordConnectRequestInfoDto.class);
    }

    @Override
    public List<DiscordConnectRequestInfoDto> findRequestToMyCountry(Principal principal, Long idGame) {
        CountryModel country = countryService.findByGameIdAndUserLogin(idGame, principal);
        return Builder.map(discordConnectRequestRepository.findAllByCountryTo_IdAndDiscordRequest(country.getId(), DiscordRequest.NEW), DiscordConnectRequestInfoDto.class);
    }

    @Override
    public DiscordConnectRequestInfoDto create(DiscordConnectRequestCreateDto connectRequestCreate, Principal principal) {
        CountryModel country = countryService.findByGameIdAndUserLogin(connectRequestCreate.getGame().getId(), principal);
        DiscordConnectRequestModel discordConnectRequest = discordConnectRequestRepository.save(
                new DiscordConnectRequestModel()
                        .setCountryFrom(country)
                        .setCountryTo(countryService.findOne(connectRequestCreate.getCountryTo().getId()))
                        .setEnd(false)
                        .setStep(country.getGame().getCurrentStep())
                        .setDiscordRequest(DiscordRequest.NEW)
        );
        discordConnectRequest.setUsers(
                connectRequestCreate.getUsers().stream().map(userIdDto ->
                        userDiscordRepository.save(
                                new UserDiscordModel()
                                        .setUser(userService.findOne(userIdDto.getId()))
                                        .setDiscordConnectRequest(discordConnectRequest)
                        )
                ).collect(Collectors.toList()));
        webSocketService.sendUpdateDiscordRequestFrom(discordConnectRequest.getCountryFrom().getId());
        webSocketService.sendUpdateDiscordRequestTo(discordConnectRequest.getCountryTo().getId());
        return Builder.map(discordConnectRequest, DiscordConnectRequestInfoDto.class);
    }

    @Override
    public DiscordConnectRequestModel confirm(Long idDiscordConnectRequest) {
        DiscordConnectRequestModel request = discordConnectRequestRepository.findById(idDiscordConnectRequest).orElseThrow(RuntimeException::new);
        discordConnectRequestRepository.save(
                request
                        .setDiscordRequest(DiscordRequest.CONFIRM)
        );
        request.getUsers().forEach(userDiscordModel -> {
            discordAction.moveUserInChanel(userDiscordModel.getUser().getLogin(), request.getCountryTo().getName());
        });
        webSocketService.sendUpdateDiscordRequestFrom(request.getCountryFrom().getId());
        webSocketService.sendUpdateDiscordRequestTo(request.getCountryTo().getId());
        return request;
    }

    @Override
    public DiscordConnectRequestModel reject(Long idDiscordConnectRequest) {
        DiscordConnectRequestModel request = discordConnectRequestRepository.findById(idDiscordConnectRequest).orElseThrow(RuntimeException::new);
        discordConnectRequestRepository.save(
                request
                        .setDiscordRequest(DiscordRequest.REJECTED)
        );
        webSocketService.sendUpdateDiscordRequestFrom(request.getCountryFrom().getId());
        webSocketService.sendUpdateDiscordRequestTo(request.getCountryTo().getId());
        return request;
    }

    @Override
    public DiscordConnectRequestModel end(Long idDiscordConnectRequest) {
        DiscordConnectRequestModel request = discordConnectRequestRepository.findById(idDiscordConnectRequest).orElseThrow(RuntimeException::new);
        discordConnectRequestRepository.save(
                request
                        .setEnd(true)
        );
        request.getUsers().forEach(userDiscordModel -> {
            discordAction.moveUserInChanel(userDiscordModel.getUser().getLogin(), request.getCountryFrom().getName());
        });
        webSocketService.sendUpdateDiscordRequestFrom(request.getCountryFrom().getId());
        webSocketService.sendUpdateDiscordRequestTo(request.getCountryTo().getId());
        return request;
    }

    @Override
    public void allGoInGlobal(Long idGame) {
        gameService.findOne(idGame).getCountry().stream().flatMap(countryModel -> countryModel.getUserCountry().stream())
                .map(UserCountryModel::getUser)
                .forEach(userModel -> {
                    discordAction.moveUserInChanel(userModel.getLogin(), GLOBAL_CHANNEL);
                });
    }

    @Override
    public void allCountryGoHome(Long idGame) {
        gameService.findOne(idGame).getCountry().forEach(countryModel -> {
            countryModel.getUserCountry().forEach(userCountryModel -> {
                discordAction.moveUserInChanel(userCountryModel.getUser().getLogin(), countryModel.getName());
            });
        });
    }

    @Override
    public void closeAllRequestByStepAndGameId(Step step, Long idGame) {
        discordConnectRequestRepository.findAllByCountryTo_Game_IdAndStepAndDiscordRequest(idGame, step, DiscordRequest.NEW).forEach(dcrm -> {
            reject(dcrm.getId());
        });
    }
}
