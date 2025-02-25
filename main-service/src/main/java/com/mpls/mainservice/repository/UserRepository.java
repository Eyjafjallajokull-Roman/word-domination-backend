package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    UserModel findByLogin(String login);

}
