package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.NominationDto;
import com.mpls.mainservice.model.CountryModel;

import java.util.List;

public interface NominationService {

    Long calculateDonateFromCountry(Long idCountry);

    List<CountryModel> findMaxDonateFromCountry(Long idGame, NominationDto nomination);

    Long calculateUseWeaponCountry(Long idCountry);

    List<CountryModel> findMaxUseWeaponCountry(Long idGame, NominationDto nomination);

    List<CountryModel> findMinUseWeaponCountry(Long idGame, NominationDto nomination);

    Long calculateEcologyCountry(Long idCountry);

    List<CountryModel> findMaxEcologyCountry(Long idGame, NominationDto nomination);

    Long calculateUpdateCityCountry(Long idCountry);

    List<CountryModel> findMaxUpdateCityCountry(Long idGame, NominationDto nomination);

    NominationDto findNomination(Long idGame);

}
