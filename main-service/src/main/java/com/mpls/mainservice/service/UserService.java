package com.mpls.mainservice.service;

import com.mpls.mainservice.dto.create.UserCreateDto;
import com.mpls.mainservice.model.UserModel;
import org.apache.catalina.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<UserModel> findAll();

    UserModel findOne(Long id);

    void deleteById(Long id);

    UserModel save(UserModel userModel);

    UserModel create(UserCreateDto userCreateDto);

    UserModel findByLogin(Principal principal);
    UserModel findByLogin(String principal);
}
