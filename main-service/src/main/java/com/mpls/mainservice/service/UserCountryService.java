package com.mpls.mainservice.service;

import com.mpls.mainservice.model.UserCountryModel;

import java.security.Principal;
import java.util.List;

public interface UserCountryService {


    UserCountryModel addUserToCountry(Principal principal, Long idCountry);

    List<UserCountryModel> findAll();

    UserCountryModel findOne(Long id);

    void deleteById(Long id);

    UserCountryModel save(UserCountryModel userCountryModel);
}
