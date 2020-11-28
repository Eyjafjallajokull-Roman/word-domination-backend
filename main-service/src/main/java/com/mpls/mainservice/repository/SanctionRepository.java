package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.SanctionModel;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanctionRepository extends JpaRepository<SanctionModel, Long> {

    List<SanctionModel> findAllByCountry_IdAndDecision_Step(Long country_id, Step decision_step);

    Long countByCountry_IdAndDecision_Step(Long country_id, Step decision_step);

    @Query(value = "select * from sanction_model sm2 " +
            " where sm2.id in (select sm.id from sanction_model sm join country_model cm on cm.id = sm.country_id join decision_model dm on sm.decision_id = dm.id " +
            " where dm.step=:STEP and sm.country_id=:ID_COUNTRY) ", nativeQuery = true)
    List<SanctionModel> findByStepAndCountry(@Param("STEP") String step, @Param("ID_COUNTRY") Long idCountry);

}

