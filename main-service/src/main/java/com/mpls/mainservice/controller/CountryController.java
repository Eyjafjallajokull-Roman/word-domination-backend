package com.mpls.mainservice.controller;

import com.mpls.builder.Builder;
import com.mpls.mainservice.dto.DonatedMoneyCreateDto;
import com.mpls.mainservice.dto.command.CountryFullInfoCommandDto;
import com.mpls.mainservice.dto.command.SanctionCommandDto;
import com.mpls.mainservice.dto.create.DecisionCreateDto;
import com.mpls.mainservice.dto.ecology.graphic.EcologyGraphicDto;
import com.mpls.mainservice.dto.info.CountryInfoDto;
import com.mpls.mainservice.dto.statistic.CountryStatisticDto;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.SanctionRepository;
import com.mpls.mainservice.service.CountryService;
import com.mpls.mainservice.service.DecisionService;
import com.mpls.mainservice.service.DonatedMoneyService;
import com.mpls.mainservice.service.EcologyService;
import com.mpls.mainservice.validator.annotation.DonateMoneyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.mpls.builder.Builder.map;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private DonatedMoneyService donatedMoneyService;

    @Autowired
    private DecisionService decisionService;

    @Autowired
    private EcologyService ecologyService;

    @Autowired
    private SanctionRepository sanctionRepository;

    @GetMapping("/find-command-info")
    private ResponseEntity<CountryFullInfoCommandDto> findCommandInfo(Principal principal, @RequestParam("idGame") long idGame) {
        return ResponseEntity.ok(countryService.findCountryFullInfoCommandDto(idGame, principal));
    }
    @GetMapping("/test")
    private ResponseEntity<List<SanctionCommandDto>> test(@RequestParam("idGame") long idGame) {
        return ResponseEntity.ok(Builder.map(sanctionRepository.findByStepAndCountry(Step.FOURTH.name(), idGame),SanctionCommandDto.class));
    }

    @GetMapping("/find-global-statistic")
    private ResponseEntity<List<CountryStatisticDto>> findGlobalStatistic(@RequestParam("idGame") long idGame) {
        return ResponseEntity.ok(countryService.findAllGlobalStatistic(idGame));
    }

    @GetMapping("/find-ecology-graphic")
    private ResponseEntity<List<EcologyGraphicDto>> findEcologyGraphic(@RequestParam("idGame") long idGame) {
        return ResponseEntity.ok(ecologyService.findEcologyGraphic(idGame));
    }

    @GetMapping("/find-all-country-with-city")
    private ResponseEntity<List<CountryInfoDto>> findAllByGame(@RequestParam("idGame") long idGame) {
        final List<CountryModel> allCountriesByGame = countryService.findAllByGame(idGame);
        return ResponseEntity.ok(map(allCountriesByGame, CountryInfoDto.class));
    }

    @PostMapping("/donate")
    private ResponseEntity<Long> donate(Principal principal, @RequestParam("idGame") Long idGame, @Valid @RequestBody DonatedMoneyCreateDto createDto) {
        return ResponseEntity.ok(donatedMoneyService.donate(createDto, idGame, principal).getPrice());
    }

    @PostMapping("/create-decision")
    private ResponseEntity<Long> createDecision(Principal principal, @Valid @RequestBody DecisionCreateDto createDto) {
        return ResponseEntity.ok(decisionService.create(createDto, principal).getId());
    }
}
