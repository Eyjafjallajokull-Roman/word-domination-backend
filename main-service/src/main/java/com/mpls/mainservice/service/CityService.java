package com.mpls.mainservice.service;

import com.mpls.mainservice.config.CountryList;
import com.mpls.mainservice.model.CityModel;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.enums.Step;

import java.util.List;

public interface CityService {

    List<CityModel> create(CountryList countryList, CountryModel countryModel);

    Long countByCityIdAndSessionSessionNumber(Long city_id, Step session_step);

    Long countByCityIdAndSessionSessionNumberCollected(Long city_id, Step session_step);

    List<CityModel> findAll();

    CityModel findOne(Long id);

    void deleteById(Long id);

    CityModel save(CityModel cityModel);
}
