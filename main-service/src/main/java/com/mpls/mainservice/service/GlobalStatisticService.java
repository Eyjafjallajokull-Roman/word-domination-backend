package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.base.UsedWeaponBaseDto;
import com.mpls.mainservice.dto.statistic.GlobalDecisionDto;
import com.mpls.mainservice.dto.statistic.GlobalStatisticDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.UsedWeaponModel;
import com.mpls.mainservice.model.enums.Step;

import java.math.BigDecimal;
import java.util.List;

public interface GlobalStatisticService {

    List<GlobalStatisticDto> parseGlobalStatisticDto(Long idGame);
    GlobalDecisionDto parseGlobalStatisticDto(Step step, CountryModel country);

}
