package com.disneyapiproject.controllers;

import com.disneyapiproject.dao.UserDao;
import com.disneyapiproject.models.Usuarios;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private UserDao userdao;

    @RequestMapping (value = "api/registrar", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuarios user){

        //Hasheo contraseña con Argon2
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
       String hash = argon2.hash(1,1024,1,user.getPassword());
        user.setPassword(hash);
        userdao.registrar(user);

    }
}
