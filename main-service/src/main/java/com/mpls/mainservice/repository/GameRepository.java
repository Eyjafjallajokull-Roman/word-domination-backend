package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.GameModel;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameModel, Long> {

    Page<GameModel> findAllByCurrentStep(Step currentStep, Pageable pageable);

    @Query(value = "select * " +
            "from game_model gm2 " +
            "where gm2.id in (select gm.id " +
            "                 from game_model gm " +
            "                          join country_model cm on gm.id = cm.game_id " +
            "                          join user_country_model ucm on cm.id = ucm.country_id " +
            "                          join user_model um on ucm.user_id = um.id " +
            "                 where " +
            "                    um.login = :LOGIN)",
            countQuery = "select count(*) " +
                    "from game_model gm2 " +
                    "where gm2.id in (select gm.id " +
                    "                 from game_model gm " +
                    "                          join country_model cm on gm.id = cm.game_id " +
                    "                          join user_country_model ucm on cm.id = ucm.country_id " +
                    "                          join user_model um on ucm.user_id = um.id " +
                    "                 where " +
                    "                    um.login = :LOGIN)"
            , nativeQuery = true)
    Page<GameModel> findAllMyGame(@Param("LOGIN") String login,
                                  Pageable pageable);
}
