package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.EcologyModel;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcologyRepository extends JpaRepository<EcologyModel, Long> {
    Long countByGame_Id(Long game_id);
    Long countByGame_IdAndDecision_Step(Long game_id, Step decision_Step);
    Long countByDecision_Country_IdAndDecision_Step(Long decision_country_id, Step decision_Step);
    Long countByDecision_Country_Id(Long decision_country_id);

}
