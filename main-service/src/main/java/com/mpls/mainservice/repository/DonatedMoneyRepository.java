package com.mpls.mainservice.repository;

import com.mpls.mainservice.model.DonatedMoneyModel;
import com.mpls.mainservice.model.enums.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonatedMoneyRepository extends JpaRepository<DonatedMoneyModel, Long> {

    List<DonatedMoneyModel> findAllByFrom_IdAndStep(Long from_id, Step step);
    List<DonatedMoneyModel> findAllByTo_IdAndStep(Long to_id, Step step);
    List<DonatedMoneyModel> findAllByFrom_Id(Long from_id);


}
