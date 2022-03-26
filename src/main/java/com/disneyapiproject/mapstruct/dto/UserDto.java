package com.disneyapiproject.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {
    @Id
    private Long id;
    @Email @NotBlank
    private String email;
    private String name;
    @NotBlank @Size(min =8)
    private String password;
}
