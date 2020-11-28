package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.UsedWeaponModel;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsedWeaponRepository extends JpaRepository<UsedWeaponModel,Long> {

    Long countByDecision_Country_Game_Id(Long decision_country_game_id);
    Long countByDecision_Country_Id(Long decision_country_id);
    Long countByDecision_Country_Game_IdAndDecision_Step(Long decision_country_game_id, Step decision_step);
    Long countByWeapon_Id(Long weapon_id);
    List<UsedWeaponModel> findAllByDecision_Country_IdAndDecision_Step(Long idCountry, Step decision_step);
}
