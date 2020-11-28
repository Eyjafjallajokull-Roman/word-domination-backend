package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.UserCountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCountryRepository extends JpaRepository<UserCountryModel,Long> {
    Long countByCountry_Game_IdAndUser_Login(Long country_game_id, String user_login);
    Long countByCountry_IdAndUser_Login(Long country_id, String user_login);
    Long countByCountry_Game_Id(Long country_game_id);
}
