package com.disneyapiproject.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class MovieSlimDto {

    private Long id;
    private String image;
    private String title;
    private LocalDateTime date;

}
