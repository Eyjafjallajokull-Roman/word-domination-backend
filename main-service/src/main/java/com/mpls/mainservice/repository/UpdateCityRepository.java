package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.UpdateCityModel;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateCityRepository extends JpaRepository<UpdateCityModel,Long> {
    Long countByDecision_Country_IdAndDecision_Step(Long decision_country_id, Step decision_step);
    Long countByCity_IdAndDecision_Step(Long city_id, Step decision_step);
    Long countByDecision_Country_Id(Long decision_country_id);
}
