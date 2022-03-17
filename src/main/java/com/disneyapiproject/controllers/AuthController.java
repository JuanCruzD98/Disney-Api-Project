package com.disneyapiproject.controllers;

import com.disneyapiproject.dao.UserDao;
import com.disneyapiproject.models.Usuarios;
import com.disneyapiproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/auth/login", method = RequestMethod.POST)
    public void login(@RequestBody Usuarios user){


       };

    }


    


