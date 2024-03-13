package com.example.mapping_practise.service;

import com.example.mapping_practise.dto.requestDTO.CityRequestDto;
import com.example.mapping_practise.model.City;
import com.example.mapping_practise.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City addCity(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    @Override
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();

        cityRepository.findAll().forEach(cities::add);
        return cities;
    }

    @Override
    public City getCity(Long cityId) {

        return cityRepository.findById(cityId).orElseThrow(
                ()->new IllegalArgumentException("city with cityId : "+cityId+"could not found"));

    }

    @Override
    public City deleteCity(Long cityId) {
        City city = getCity(cityId);
        cityRepository.delete(city);
        return city;
    }

    @Override
    public City editCity(Long cityId,CityRequestDto cityRequestDto) {

        City toeditCity = getCity(cityId);
        toeditCity.setName(cityRequestDto.getName());
        return toeditCity;
    }
}
