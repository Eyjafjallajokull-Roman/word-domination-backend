package com.mpls.mainservice.service.impl;

import com.mpls.mainservice.config.CountryList;
import com.mpls.mainservice.model.CityModel;
import com.mpls.mainservice.model.CountryModel;
import com.mpls.mainservice.model.enums.Step;
import com.mpls.mainservice.repository.CityRepository;
import com.mpls.mainservice.repository.UpdateCityRepository;
import com.mpls.mainservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UpdateCityRepository updateCityRepository;

    @Override
    public Long countByCityIdAndSessionSessionNumber(Long city_id, Step session_step) {
        return updateCityRepository.countByCity_IdAndDecision_Step(city_id, session_step);
    }

    @Override
    public Long countByCityIdAndSessionSessionNumberCollected(Long city_id, Step step) {
        Long count = 0L;
        Step sn = Step.START;
        do {
            count += updateCityRepository.countByCity_IdAndDecision_Step(city_id, sn);
            sn = sn.next();
        } while ((!sn.equals(step.next())) && !step.equals(Step.START));
        return count;
    }

    @Override
    public List<CityModel> create(CountryList countryList, CountryModel countryModel) {
        return countryList.getCityList().entrySet().stream().map(a ->
                cityRepository.save(new CityModel()
                        .setShield(false)
                        .setRuined(false)
                        .setColor(a.getValue().getColor())
                        .setName(a.getValue().getName())
                        .setPhoto(a.getValue().getPhoto())
                        .setCountry(countryModel))
        ).collect(Collectors.toList());
    }

    @Override
    public List<CityModel> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public CityModel findOne(Long id) {
        return cityRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public CityModel save(CityModel cityModel) {
        return cityRepository.save(cityModel);
    }
}
