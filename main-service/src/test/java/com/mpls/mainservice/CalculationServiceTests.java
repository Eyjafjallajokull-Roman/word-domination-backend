package com.mpls.mainservice;

import com.mpls.mainservice.model.*;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.*;
import com.mpls.mainservice.service.CalculationService;
import com.mpls.mainservice.service.EcologyService;
import com.mpls.mainservice.service.UsedWeaponService;
import com.mpls.mainservice.service.impl.CalculationServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CalculationServiceTests {


    private static final GameModel game = new GameModel().setCurrentStep(Step.FIRST).setId(1L);
    private static final CountryModel country = new CountryModel().setId(1L).setGame(game);
    private static final List<CityModel> city = Arrays.asList(
            new CityModel()
                    .setCountry(country)
                    .setUpdateCity(Arrays.asList(
                            new UpdateCityModel()
                                    .setDecision(
                                            new DecisionModel().setStep(Step.FIRST)
                                    ),
                            new UpdateCityModel()
                                    .setDecision(
                                            new DecisionModel().setStep(Step.SECOND)
                                    )
                    ))
                    .setRuined(true)
                    .setStepRuined(Step.SECOND),
            new CityModel()
                    .setCountry(country)
                    .setUpdateCity(Arrays.asList(
                            new UpdateCityModel()
                                    .setDecision(
                                            new DecisionModel().setStep(Step.FIRST)
                                    )

                    ))
                    .setRuined(false)
            ,
            new CityModel()
                    .setCountry(country)
                    .setUpdateCity(Arrays.asList(
                            new UpdateCityModel()
                                    .setDecision(
                                            new DecisionModel().setStep(Step.FIRST)
                                    )
                    ))
                    .setRuined(false),
            new CityModel()
                    .setCountry(country)
                    .setUpdateCity(new ArrayList<>())
                    .setRuined(false)

    );

    @TestConfiguration
    static class CalculationServiceImplTestContextConfiguration {

        @Bean
        public CalculationService calculationService() {
            return new CalculationServiceImpl();
        }
    }

    @Autowired
    private CalculationService calculationService;

    @MockBean
    private EcologyRepository ecologyRepository;
    @MockBean
    private WeaponRepository weaponRepository;
    @MockBean
    private DecisionRepository decisionRepository;
    @MockBean
    private UpdateCityRepository updateCityRepository;
    @MockBean
    private DonatedMoneyRepository donatedMoneyRepository;
    @MockBean
    private SanctionRepository sanctionRepository;
    @MockBean
    private UsedWeaponRepository usedWeaponRepository;

    @BeforeEach
    public void setUp() {
//START
        stepConfigMock(1L, Step.START, 0L, 0L, 0L, 0L, Arrays.asList(), Arrays.asList(), 0L, 0L);
//FIRST
        stepConfigMock(1L, Step.FIRST, 0L, 0L, 7L, 3L, Arrays.asList(), Arrays.asList(), 0L, 0L);
//SECOND
        stepConfigMock(1L, Step.SECOND, 0L, 4L, 0L, 4L, Arrays.asList(), Arrays.asList(), 0L, 0L);
//THIRD
        stepConfigMock(1L, Step.THIRD, 0L, 2L, 0L, 1L, Arrays.asList(), Arrays.asList(), 0L, 0L);
//FOURTH
        stepConfigMock(1L, Step.FOURTH, 0L, 2L, 0L, 1L, Arrays.asList(), Arrays.asList(), 0L, 0L);
//FIFTH
        stepConfigMock(1L, Step.FIFTH, 0L, 2L, 0L, 1L, Arrays.asList(), Arrays.asList(), 0L, 0L);
//SIXTH
        stepConfigMock(1L, Step.SIXTH, 0L, 2L, 0L, 1L, Arrays.asList(), Arrays.asList(), 0L, 0L);
//END
        stepConfigMock(1L, Step.END, 0L, 2L, 0L, 1L, Arrays.asList(), Arrays.asList(), 0L, 0L);
    }

    private void stepConfigMock(Long idCountry, Step step, Long ecology, Long countWeapon, Long countCreateWeaponTechnology, Long countUpgradeCity, List<DonatedMoneyModel> from, List<DonatedMoneyModel> to, Long countSanction, Long usedWeapon) {
        Mockito.when(ecologyRepository.countByDecision_Country_IdAndDecision_Step(idCountry, step))
                .thenReturn(ecology);
        Mockito.when(weaponRepository.countByDecision_Country_IdAndDecision_Step(idCountry, step))
                .thenReturn(countWeapon);
        Mockito.when(decisionRepository.countByCountry_IdAndStepAndWeaponTechnology(idCountry, step, true))
                .thenReturn(countCreateWeaponTechnology);
        Mockito.when(updateCityRepository.countByDecision_Country_IdAndDecision_Step(idCountry, step))
                .thenReturn(countUpgradeCity);
        Mockito.when(donatedMoneyRepository.findAllByFrom_IdAndStep(idCountry, step))
                .thenReturn(from);
        Mockito.when(donatedMoneyRepository.findAllByTo_IdAndStep(idCountry, step))
                .thenReturn(to);
        Mockito.when(sanctionRepository.countByCountry_IdAndDecision_Step(idCountry, step))
                .thenReturn(countSanction);
        Mockito.when(ecologyRepository.countByGame_IdAndDecision_Step(game.getId(), step))
                .thenReturn(ecology);
        Mockito.when(weaponRepository.countByDecision_Country_Game_IdAndDecision_Step(game.getId(), step))
                .thenReturn(countWeapon);
        Mockito.when(usedWeaponRepository.countByDecision_Country_Game_IdAndDecision_Step(game.getId(), step))
                .thenReturn(usedWeapon);
        Mockito.when(decisionRepository.countByCountry_Game_IdAndStepAndWeaponTechnology(game.getId(), step, true))
                .thenReturn(countCreateWeaponTechnology);
    }


    @Test
    public void calculationIncomeEconomyTest() {
        assertThat(
                calculationService.calculationIncomeEconomy(
                        new CountryModel()
                                .setId(1L)
                                .setGame(
                                        game
                                )
                                .setCity(city))
        )
                .isEqualTo(2027L);

    }
}
