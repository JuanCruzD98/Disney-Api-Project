package com.disneyapiproject.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class MovieDto {

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Long Id;

    @NotNull
    private String title;

    @NotNull
    private String image;

    @NotNull
    private Date date;
    @NotNull
    private Integer rating;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CharacterSlimDto> characters;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<GenreSlimDto> genres;
}
