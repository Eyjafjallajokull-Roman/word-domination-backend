package com.mpls.mainservice.controller;

import com.mpls.mainservice.dto.command.CountryFullInfoCommandDto;
import com.mpls.mainservice.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping("/next-step")
    private ResponseEntity nextStep(@RequestParam("idGame") long idGame) {
        actionService.nextSession(idGame);
        return ResponseEntity.ok().build();
    }
}
