package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityModel,Long> {

    List<CityModel> findAllByCountry_Game_Id(Long country_game_id);

}
