package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.UserCountryModel;
import com.mpls.mainservice.repository.UserCountryRepository;
import com.mpls.mainservice.repository.UserRepository;
import com.mpls.mainservice.service.CountryService;
import com.mpls.mainservice.service.UserCountryService;
import com.mpls.mainservice.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserCountryServiceImpl implements UserCountryService {

    @Autowired
    private UserCountryRepository userCountryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private WebSocketService webSocketService;

    @Override
    public UserCountryModel addUserToCountry(Principal principal, Long idCountry) {
        CountryModel countryModel = countryService.findOne(idCountry);
        countryModel.getUserCountry().stream().filter(userCountryModel -> userCountryModel.getUser().getLogin().equals(principal.getName())).findFirst().ifPresent(userCountryModel -> {
            throw new RuntimeException();
        });
        UserCountryModel model = userCountryRepository
                .save(new UserCountryModel().setCountry(countryModel).setUser(userRepository.findByLogin(principal.getName()))).setGame(countryModel.getGame());
        webSocketService.sendUpdateUserGame(model.getGame().getId());
        webSocketService.sendUpdateUserCountry(model.getCountry().getId());
        webSocketService.sendReloadGame();
        return model;
    }

    @Override
    public List<UserCountryModel> findAll() {
        return userCountryRepository.findAll();
    }

    @Override
    public UserCountryModel findOne(Long id) {
        return userCountryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        userCountryRepository.deleteById(id);
    }

    @Override
    public UserCountryModel save(UserCountryModel userCountryModel) {
        return userCountryRepository.save(userCountryModel);
    }
}
