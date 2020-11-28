package com.mpls.mainservice.controller;

import com.mpls.mainservice.dto.discord.DiscordConnectRequestInfoDto;
import com.mpls.mainservice.dto.discord.create.DiscordConnectRequestCreateDto;
import com.mpls.mainservice.service.DiscordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/discord")
@RestController
public class DiscordController {

    @Autowired
    private DiscordService discordService;

    @GetMapping("/find-my-request")
    private ResponseEntity<List<DiscordConnectRequestInfoDto>> findMyRequest(Principal principal, @RequestParam Long idGame) {
        return ResponseEntity.ok(discordService.findMyRequest(principal, idGame));
    }

    @GetMapping("/find-request-to-my-country")
    private ResponseEntity<List<DiscordConnectRequestInfoDto>> findRequestToMyCountry(Principal principal, @RequestParam Long idGame) {
        return ResponseEntity.ok(discordService.findRequestToMyCountry(principal, idGame));
    }

    @PostMapping("/create")
    private ResponseEntity<DiscordConnectRequestInfoDto> create(@RequestBody DiscordConnectRequestCreateDto connectRequestCreate, Principal principal) {
        return ResponseEntity.ok(discordService.create(connectRequestCreate, principal));
    }

    @PostMapping("/confirm")
    private ResponseEntity<Void> confirm(@RequestParam Long idDiscordConnectRequest) {
        discordService.confirm(idDiscordConnectRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reject")
    private ResponseEntity<Void> reject(@RequestParam Long idDiscordConnectRequest) {
        discordService.reject(idDiscordConnectRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/end")
    private ResponseEntity<Void> end(@RequestParam Long idDiscordConnectRequest) {
        discordService.end(idDiscordConnectRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/all-go-in-global")
    private ResponseEntity<Void> allGoInGlobal(@RequestParam Long idGame) {
        discordService.allGoInGlobal(idGame);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/all-country-go-home")
    private ResponseEntity<Void> allCountryGoHome(@RequestParam Long idGame) {
        discordService.allCountryGoHome(idGame);
        return ResponseEntity.ok().build();
    }


}
