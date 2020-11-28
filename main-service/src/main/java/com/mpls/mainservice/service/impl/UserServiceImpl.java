package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.dto.create.UserCreateDto;
import com.mpls.mainservice.model.UserModel;
import com.mpls.mainservice.repository.UserRepository;
import com.mpls.mainservice.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findOne(Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public UserModel create(UserCreateDto userCreateDto) {
        ofNullable(userRepository.findByLogin(userCreateDto.getLogin())).ifPresent(userModel -> {
            throw new RuntimeException();
        });
        return save(new UserModel()
                .setFirstName(userCreateDto.getFirstName())
                .setLastName(userCreateDto.getLastName())
                .setPassword(passwordEncoder.encode(userCreateDto.getPassword()))
                .setLogin(userCreateDto.getLogin()));
    }

    @Override
    public UserModel findByLogin(Principal principal) {
        if (!ofNullable(principal).isPresent()) {
            throw new UsernameNotFoundException("user not found");
        }
        return findByLogin(principal.getName());
    }

    @Override
    public UserModel findByLogin(String principal) {
        UserModel model = userRepository.findByLogin(principal);
        if (!ofNullable(model).isPresent()) {
            throw new UsernameNotFoundException("user not found");
        }
        return model;
    }
}
