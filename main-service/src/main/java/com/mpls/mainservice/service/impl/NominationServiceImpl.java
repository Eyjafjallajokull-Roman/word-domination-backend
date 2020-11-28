package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.dto.CountryBaseDto;
import com.mpls.mainservice.dto.NominationDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.DonatedMoneyModel;
import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.*;
import com.mpls.mainservice.service.GameService;
import com.mpls.mainservice.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.mpls.builder.Builder.map;

@Service
public class NominationServiceImpl implements NominationService {

    @Autowired
    private DonatedMoneyRepository donatedMoneyRepository;
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private UsedWeaponRepository usedWeaponRepository;

    @Autowired
    private EcologyRepository ecologyRepository;

    @Autowired
    private UpdateCityRepository updateCityRepository;

    @Override
    public Long calculateDonateFromCountry(Long idCountry) {
        AtomicReference<Long> donate = new AtomicReference<>(0L);
        donatedMoneyRepository.findAllByFrom_Id(idCountry).stream().map(DonatedMoneyModel::getPrice).forEach(d -> {
            donate.updateAndGet(v -> v + d);
        });
        return donate.get();
    }

    @Override
    public List<CountryModel> findMaxDonateFromCountry(Long idGame, NominationDto nomination) {
        List<CountryModel> list = new ArrayList<>();
        AtomicReference<Long> countMax = new AtomicReference<>(0L);
        countryRepository.findAllByGame_Id(idGame).forEach(countryModel -> {
            Long tempCountMax = calculateDonateFromCountry(countryModel.getId());
            if (tempCountMax.equals(countMax.get())) {
                list.add(countryModel);
            } else if (tempCountMax > (countMax.get())) {
                list.clear();
                list.add(countryModel);
                countMax.set(tempCountMax);
            }
        });
        nomination.setDonateCountry(map(list, CountryBaseDto.class));
        nomination.setCountDonate(countMax.get());
        return list;
    }

    @Override
    public Long calculateUseWeaponCountry(Long idCountry) {
        return usedWeaponRepository.countByDecision_Country_Id(idCountry);
    }

    @Override
    public List<CountryModel> findMaxUseWeaponCountry(Long idGame, NominationDto nomination) {
        List<CountryModel> list = new ArrayList<>();
        AtomicReference<Long> countMax = new AtomicReference<>(0L);
        countryRepository.findAllByGame_Id(idGame).forEach(countryModel -> {
            Long tempCountMax = calculateUseWeaponCountry(countryModel.getId());
            if (tempCountMax.equals(countMax.get())) {
                list.add(countryModel);
            } else if (tempCountMax > (countMax.get())) {
                list.clear();
                list.add(countryModel);
                countMax.set(tempCountMax);
            }
        });
        nomination.setMaxUseWeaponCountry(map(list, CountryBaseDto.class));
        nomination.setCountMaxUseWeapon(countMax.get());
        return list;
    }

    @Override
    public List<CountryModel> findMinUseWeaponCountry(Long idGame, NominationDto nomination) {
        List<CountryModel> list = new ArrayList<>();
        AtomicReference<Long> countMin = new AtomicReference<>(1000L);
        countryRepository.findAllByGame_Id(idGame).forEach(countryModel -> {
            Long tempCountMax = calculateUseWeaponCountry(countryModel.getId());
            if (tempCountMax.equals(countMin.get())) {
                list.add(countryModel);
            } else if (tempCountMax < (countMin.get())) {
                list.clear();
                list.add(countryModel);
                countMin.set(tempCountMax);
            }
        });
        nomination.setMinUseWeapon(map(list, CountryBaseDto.class));
        nomination.setCountMinUseWeapon(countMin.get());
        return list;
    }

    @Override
    public Long calculateEcologyCountry(Long idCountry) {
        return ecologyRepository.countByDecision_Country_Id(idCountry);
    }

    @Override
    public List<CountryModel> findMaxEcologyCountry(Long idGame, NominationDto nomination) {
        List<CountryModel> list = new ArrayList<>();
        AtomicReference<Long> countMax = new AtomicReference<>(0L);
        countryRepository.findAllByGame_Id(idGame).forEach(countryModel -> {
            Long tempCountMax = calculateEcologyCountry(countryModel.getId());
            if (tempCountMax.equals(countMax.get())) {
                list.add(countryModel);
            } else if (tempCountMax > (countMax.get())) {
                list.clear();
                list.add(countryModel);
                countMax.set(tempCountMax);
            }
        });
        nomination.setEcologyCountry(map(list, CountryBaseDto.class));
        nomination.setCountEcology(countMax.get());
        return list;
    }

    @Override
    public Long calculateUpdateCityCountry(Long idCountry) {
        return updateCityRepository.countByDecision_Country_Id(idCountry);
    }

    @Override
    public List<CountryModel> findMaxUpdateCityCountry(Long idGame, NominationDto nomination) {
        List<CountryModel> list = new ArrayList<>();
        AtomicReference<Long> countMax = new AtomicReference<>(0L);
        countryRepository.findAllByGame_Id(idGame).forEach(countryModel -> {
            Long tempCountMax = calculateUpdateCityCountry(countryModel.getId());
            if (tempCountMax.equals(countMax.get())) {
                list.add(countryModel);
            } else if (tempCountMax > (countMax.get())) {
                list.clear();
                list.add(countryModel);
                countMax.set(tempCountMax);
            }
        });
        nomination.setUpdateCityCountry(map(list, CountryBaseDto.class));
        nomination.setCountUpdateCity(countMax.get());
        return list;
    }

    @Override
    public NominationDto findNomination(Long idGame) {
        GameModel gameModel = gameService.findOne(idGame);
        if (!gameModel.getCurrentStep().next().equals(Step.END)) {
            throw new RuntimeException();
        }
        NominationDto nomination = new NominationDto();
        findMaxDonateFromCountry(idGame, nomination);
        findMaxUseWeaponCountry(idGame, nomination);
        findMinUseWeaponCountry(idGame, nomination);
        findMaxEcologyCountry(idGame, nomination);
        findMaxUpdateCityCountry(idGame, nomination);
        return nomination;
    }

}
