package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.GameModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryModel, Long> {
    @Query(value = "select * " +
            "from country_model cm2 " +
            "where cm2.id in (select cm.id " +
            "                 from game_model gm " +
            "                          join country_model cm on gm.id = cm.game_id " +
            "                          join user_country_model ucm on cm.id = ucm.country_id " +
            "                          join user_model um on ucm.user_id = um.id " +
            "                 where gm.id = :IDGAME " +
            "                   and um.login = :LOGIN)", nativeQuery = true)
    CountryModel findByGameIdAndUserLogin(@Param("IDGAME") Long idGame, @Param("LOGIN") String login);

    List<CountryModel> findAllByGame_Id(Long game_id);

    Long countByGame_Id(Long game_id);

}
