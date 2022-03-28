
package com.disneyapiproject.user;


import com.disneyapiproject.mapstruct.dto.LoginResponseDto;
import com.disneyapiproject.mapstruct.dto.UserDto;
import com.disneyapiproject.security.CustomUserDetailsService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class UserController {

    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;


    @PostMapping(value = "/api/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping("api/auth/login")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody UserDto user) {

        return new ResponseEntity<>(new LoginResponseDto(userService.logInUser(user)), HttpStatus.OK);

    }
}







