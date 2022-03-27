package com.disneyapiproject.user;

import com.disneyapiproject.mapstruct.dto.LoginResponseDto;
import com.disneyapiproject.mapstruct.dto.UserDto;
import com.disneyapiproject.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
        userService.registerUser(userDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> logInUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(new LoginResponseDto(userService.logInUser(userDto)), HttpStatus.OK);
    }


    }



