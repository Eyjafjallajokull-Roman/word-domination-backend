package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.CountryBaseDto;
import com.mpls.mainservice.dto.GameFullInfo;
import com.mpls.mainservice.dto.NominationDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.EcologyModel;
import com.mpls.mainservice.model.GameModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface GameService {


    GameModel create(Long countCountry);

    Page<GameFullInfo> findAllMyGame(Pageable pageable, Principal principal);

    List<GameModel> findAll();

    Page<GameFullInfo> findAll(Pageable pageable, Principal principal);

    Page<GameFullInfo> findAllFreeGame(Pageable pageable, Principal principal);

    GameModel findOne(Long id);

    GameFullInfo findOne(Long id, Principal principal);

    GameFullInfo parseGameFullInfo(GameModel gameModel, Principal principal);

    CountryBaseDto parseCountryBaseDto(CountryModel countryModel, Principal principal);

    List<CountryBaseDto> parseCountryBaseDto(List<CountryModel> countryModel, Principal principal);

    void deleteById(Long id);

    GameModel save(GameModel gameModel);
}
