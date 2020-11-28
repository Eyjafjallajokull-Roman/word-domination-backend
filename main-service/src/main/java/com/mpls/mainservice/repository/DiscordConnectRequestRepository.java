package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.DiscordConnectRequestModel;
import com.mpls.mainservice.model.enums.DiscordRequest;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscordConnectRequestRepository extends JpaRepository<DiscordConnectRequestModel, Long> {

    List<DiscordConnectRequestModel> findAllByCountryFrom_Id(Long countryFrom_id);

    List<DiscordConnectRequestModel> findAllByCountryTo_IdAndDiscordRequest(Long countryTo_id, DiscordRequest discordRequest);

    List<DiscordConnectRequestModel> findAllByCountryTo_Game_IdAndStepAndDiscordRequest(Long countryTo_game_id, Step step, DiscordRequest discordRequest);

}
