package com.example.mapping_practise.controller;

import com.example.mapping_practise.dto.requestDTO.CityRequestDto;
import com.example.mapping_practise.model.City;
import com.example.mapping_practise.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/addCity")
    public ResponseEntity<City> addcity(@RequestBody final CityRequestDto cityRequestDto) {

        City city = cityService.addCity(cityRequestDto);
        return new ResponseEntity<City>(city, HttpStatus.OK);

    }

    @GetMapping("/getCity/{id}")
    public ResponseEntity<City> getCityById(@PathVariable final Long id) {
        City city = cityService.getCity(id);
        return new ResponseEntity<City>(city, HttpStatus.OK);
    }
    @GetMapping("/getCities")
    public ResponseEntity<List<City>> getCities(){
        List<City> cities = cityService.getCities();
        return new ResponseEntity<List<City>>(cities,HttpStatus.OK);
    }
    @DeleteMapping("/deleteCity")
    public ResponseEntity<City> deleteCity(@PathVariable final Long id){
        City city =cityService.deleteCity(id);
        return new ResponseEntity<City>(city,HttpStatus.OK);
    }
    @PostMapping("/editCity")
    public ResponseEntity<City> editCity(@RequestBody final CityRequestDto cityRequestDto,@PathVariable final Long id){
        City city = cityService.editCity(id,cityRequestDto);
        return new ResponseEntity<City>(city,HttpStatus.OK);
    }

}
