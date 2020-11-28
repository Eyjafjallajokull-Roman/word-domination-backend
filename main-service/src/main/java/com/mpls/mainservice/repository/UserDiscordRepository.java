package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.UserDiscordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDiscordRepository extends JpaRepository<UserDiscordModel,Long> {
}
