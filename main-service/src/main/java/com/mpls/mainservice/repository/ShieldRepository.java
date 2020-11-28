package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.ShieldModel;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShieldRepository extends JpaRepository<ShieldModel, Long> {
    ShieldModel findByDecision_StepAndCity_Id(Step decision_step, Long city_id);

    Long countByDecision_StepAndCity_Id(Step decision_step, Long city_id);
    Long countByCity_Country_Id(Long city_country_id);
    Long countByCity_Country_IdAndDecision_Step(Long city_country_id, Step decision_step);

    List<ShieldModel> findAllByCity_Id(Long city_id);
}
