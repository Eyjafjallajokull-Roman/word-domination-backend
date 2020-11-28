package com.mpls.mainservice.controller;

import com.mpls.builder.Builder;
import com.mpls.mainservice.dto.command.UserCommandDto;
import com.mpls.mainservice.dto.create.UserCreateDto;
import com.mpls.mainservice.service.UserCountryService;
import com.mpls.mainservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserCountryService userCountryService;

    @Autowired
    private UserService userService;

    @PostMapping("/add-to-country")
    private ResponseEntity<Void> addToCountry(@RequestParam("countryId") Long countryId, Principal principal) {
        userCountryService.addUserToCountry(principal, countryId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    private ResponseEntity<Long> create(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.ok(userService.create(userCreateDto).getId());
    }

    @GetMapping("/principal")
    private ResponseEntity<UserCommandDto> principal(Principal principal) {
        return ResponseEntity.ok(Builder.map(userService.findByLogin(principal), UserCommandDto.class));
    }
}
