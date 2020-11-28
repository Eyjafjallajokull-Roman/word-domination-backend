package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.DonatedMoneyCreateDto;
import com.mpls.mainservice.model.DonatedMoneyModel;

import java.security.Principal;

public interface DonatedMoneyService {
    DonatedMoneyModel donate(DonatedMoneyCreateDto moneyCreateDto,Long idGame ,Principal principal);

}
