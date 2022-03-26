package com.disneyapiproject.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CharacterDto {
    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    private String image;
    @NotBlank
    private String name;
    @NotBlank
    @Min(0)
    private Integer age;
    @NotBlank
    private Integer weight;
    @NotBlank
    private String history;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MovieSlimDto> movies;
}
