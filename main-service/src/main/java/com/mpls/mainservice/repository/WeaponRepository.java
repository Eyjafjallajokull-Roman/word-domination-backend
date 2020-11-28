package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.WeaponModel;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponRepository extends JpaRepository<WeaponModel, Long> {

    Long countByDecision_Country_Game_Id(Long decision_country_game_id);

    Long countByDecision_Country_Game_IdAndDecision_Step(Long decision_country_game_id, Step decision_step);

    Long countByDecision_Country_IdAndDecision_Step(Long game_id, Step decision_step);

    Long countByDecision_Country_Id(Long game_id);

    @Query(value = "select * " +
            "from weapon_model wm1 " +
            "         join decision_model dm on dm.id = wm1.decision_id " +
            "         join country_model cm on cm.id = dm.country_id " +
            "where wm1.id not in ( " +
            "    select wm.id " +
            "    from weapon_model wm " +
            "             join used_weapon_model uwm on wm.id = uwm.weapon_id " +
            ") " +
            "  and cm.id = :IDCOUNTRY ",nativeQuery = true)
    List<WeaponModel> findALlFreeWeapon(@Param("IDCOUNTRY") Long idCountry);
}
