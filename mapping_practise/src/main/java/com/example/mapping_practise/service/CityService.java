package com.example.mapping_practise.service;

import com.example.mapping_practise.dto.requestDTO.CityRequestDto;
import com.example.mapping_practise.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {

   City addCity(CityRequestDto cityRequestDto);
   List<City> getCities();

   City getCity(Long cityId);

   City deleteCity(Long cityId);

   City editCity(Long cityId,CityRequestDto cityRequestDto);


}
