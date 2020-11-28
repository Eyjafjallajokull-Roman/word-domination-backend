package com.mpls.mainservice.controller;

import com.mpls.mainservice.dto.GameFullInfo;
import com.mpls.mainservice.dto.statistic.GlobalStatisticDto;
import com.mpls.mainservice.service.GameService;
import com.mpls.mainservice.service.GlobalStatisticService;
import com.mpls.mainservice.dto.NominationDto;
import com.mpls.mainservice.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GlobalStatisticService globalStatisticService;
    @Autowired
    private NominationService nominationService;

    @GetMapping("/find-all")
    private ResponseEntity<Page<GameFullInfo>> findAll(Pageable pageable, Principal principal) {
        return ResponseEntity.ok(gameService.findAll(pageable, principal));
    }

    @GetMapping("/my-game")
    private ResponseEntity<Page<GameFullInfo>> myGame(Pageable pageable, Principal principal) {
        return ResponseEntity.ok(gameService.findAllMyGame(pageable, principal));
    }

    @GetMapping("/find-all-free")
    private ResponseEntity<Page<GameFullInfo>> findAllFreeGame(Pageable pageable, Principal principal) {
        return ResponseEntity.ok(gameService.findAllFreeGame(pageable, principal));
    }

    @GetMapping("/find-one")
    private ResponseEntity<GameFullInfo> findOne(@RequestParam("idGame") Long idGame, Principal principal) {
        return ResponseEntity.ok(gameService.findOne(idGame, principal));
    }

    @GetMapping("/find-global-statistic")
    private ResponseEntity<List<GlobalStatisticDto>> findGlobalStatistic(@RequestParam("idGame") Long idGame) {
        return ResponseEntity.ok(globalStatisticService.parseGlobalStatisticDto(idGame));
    }

    @GetMapping("/find-nomination")
    private ResponseEntity<NominationDto> findNomination(@RequestParam("idGame") Long idGame) {
        return ResponseEntity.ok(nominationService.findNomination(idGame));
    }

    @PostMapping("/create")
    private ResponseEntity<Void> create(@RequestParam("countCountry") Long countCountry) {
        gameService.create(countCountry);
        return ResponseEntity.ok().build();
    }

}
