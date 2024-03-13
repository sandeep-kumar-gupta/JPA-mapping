package com.example.mapping_practise.controller;

import com.example.mapping_practise.dto.requestDTO.ZipCodeRequestDto;
import com.example.mapping_practise.model.ZipCode;
import com.example.mapping_practise.service.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcode")
public class ZipCodeController {

    private final ZipCodeService zipCodeService;

    @Autowired
    public ZipCodeController(ZipCodeService zipCodeService) {
        this.zipCodeService = zipCodeService;
    }

    @PostMapping("/addZipcode")
    public ResponseEntity<ZipCode> addZipCode(@RequestBody ZipCodeRequestDto zipCodeRequestDto){
        ZipCode zipCode = zipCodeService.addZipCode(zipCodeRequestDto);
        return new ResponseEntity<ZipCode>(zipCode, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ZipCode> getZipCode(@PathVariable final Long id){
        ZipCode zipCode = zipCodeService.getZipCode(id);
        return new ResponseEntity<ZipCode>(zipCode, HttpStatus.OK);

    }
    public ResponseEntity<List<ZipCode>> getZipCodes(){
        List<ZipCode> zipCodes = zipCodeService.getZipCodes();
        return new ResponseEntity<List<ZipCode>>(zipCodes, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ZipCode> deleteZipcode(@PathVariable final Long id){
        ZipCode zipCode = zipCodeService.deleteZipCode(id);
        return new ResponseEntity<ZipCode>(zipCode, HttpStatus.OK);

    }

    @PostMapping("/editZipcode")
    public ResponseEntity<ZipCode> editZipCode(@RequestBody ZipCodeRequestDto zipCodeRequestDto,@PathVariable final Long id){
        ZipCode zipCode = zipCodeService.editZipCode(id,zipCodeRequestDto);
        return new ResponseEntity<ZipCode>(zipCode, HttpStatus.OK);

    }

    @PostMapping("/addCity/{cityId}/{zipcodeId}")
    public ResponseEntity<ZipCode> addCity(@RequestBody final Long cityId,@PathVariable final Long zipcodeId){
        ZipCode zipCode = zipCodeService.addCityToZipCode(zipcodeId,cityId);
        return new ResponseEntity<ZipCode>(zipCode, HttpStatus.OK);

    }

    @DeleteMapping("/deleteCity/{zipcodeId}")
    public ResponseEntity<ZipCode> deleteCity(@PathVariable final Long zipcodeId){
        ZipCode zipCode = zipCodeService.removeCityFromZipCode(zipcodeId);
        return new ResponseEntity<ZipCode>(zipCode, HttpStatus.OK);

    }

}
