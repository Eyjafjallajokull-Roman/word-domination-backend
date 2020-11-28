package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.DecisionModel;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecisionRepository extends JpaRepository<DecisionModel, Long> {
    @Query(value = "select count(*) " +
            "from decision_model dm1 " +
            "where dm1.id in ( " +
            "    select dm.id " +
            "    from decision_model dm " +
            "             join used_weapon_model uwm on dm.id = uwm.decision_id " +
            "             join city_model cm on cm.id = uwm.city_id " +
            "    where dm.step = :step " +
            "      and cm.id = :cityID " +
            ")", nativeQuery = true)
    Long countUsedWeaponInCity(@Param("cityID") Long cityId, @Param("step") String step);

    List<DecisionModel> findAllByCountry_Game_Id(Long country_game_id);

    DecisionModel findByCountry_IdAndStep(Long country_id, Step step);

    DecisionModel findByCountry_IdAndStepAndWeaponTechnology(Long country_id, Step step, Boolean weaponTechnology);

    Long countByCountry_Game_IdAndStepAndWeaponTechnology(Long country_id, Step step, Boolean weaponTechnology);
    Long countByCountry_IdAndStepAndWeaponTechnology(Long country_id, Step step, Boolean weaponTechnology);

    Long countByCountry_IdAndStep(Long country_id, Step step);
    Long countByStepAndCountry_Game_Id(Step step, Long country_game_id);
}
