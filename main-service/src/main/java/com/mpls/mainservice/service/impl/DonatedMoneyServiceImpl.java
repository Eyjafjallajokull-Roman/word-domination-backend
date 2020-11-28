package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.dto.DonatedMoneyCreateDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.DonatedMoneyModel;
import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.repository.DonatedMoneyRepository;
import com.mpls.mainservice.service.CountryService;
import com.mpls.mainservice.service.DonatedMoneyService;
import com.mpls.mainservice.service.GameService;
import com.mpls.mainservice.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class DonatedMoneyServiceImpl implements DonatedMoneyService {

    @Autowired
    private DonatedMoneyRepository donatedMoneyRepository;


    @Autowired
    private CountryService countryService;

    @Autowired
    private GameService gameService;

    @Autowired
    private WebSocketService webSocketService;

    @Override
    public DonatedMoneyModel donate(DonatedMoneyCreateDto moneyCreateDto, Long idGame, Principal principal) {
        CountryModel to = countryService.findOne(moneyCreateDto.getTo().getId());
        CountryModel from = countryService.findByGameIdAndUserLogin(idGame, principal);
        DonatedMoneyModel model = donatedMoneyRepository.save(
                new DonatedMoneyModel()
                        .setFrom(from)
                        .setTo(to)
                        .setPrice(moneyCreateDto.getPrice())
                        .setSessionNumber(gameService.findOne(idGame).getCurrentStep())
        );
        webSocketService.sendUpdateMoney(model.getFrom().getId());
        webSocketService.sendUpdateMoney(model.getTo().getId());
        return model;

    }
}
