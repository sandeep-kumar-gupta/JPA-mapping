package com.example.mapping_practise.dto.responseDTO;

import lombok.Data;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.util.List;

@Data
public class BookResponseDto {

    private Long id;

    private String name;

    private List<String> authorNames;

    private String categoryName;


}
