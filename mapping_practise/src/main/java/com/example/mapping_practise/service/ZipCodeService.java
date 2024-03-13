package com.example.mapping_practise.service;

import com.example.mapping_practise.dto.requestDTO.ZipCodeRequestDto;
import com.example.mapping_practise.model.ZipCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZipCodeService {

    ZipCode addZipCode(ZipCodeRequestDto zipCodeRequestDto);

    List<ZipCode> getZipCodes();

    ZipCode getZipCode(Long zipCodeId );

    ZipCode deleteZipCode(Long zipCodeId);

    ZipCode editZipCode(Long zipCodeId, ZipCodeRequestDto zipCodeRequestDto);

    ZipCode addCityToZipCode(Long zipCodeId,Long cityId);

    ZipCode removeCityFromZipCode(Long zipCodeId);


}
