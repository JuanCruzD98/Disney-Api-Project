package com.disneyapiproject.controllers;


import com.disneyapiproject.dao.CharacterDao;
import com.disneyapiproject.models.Personajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/characters")
@RestController
public class CharactersController {
    @Autowired
    private CharacterDao characterDao;

    @RequestMapping(value = "api/personajes/{id}", method = RequestMethod.GET)
    public List<Personajes> listarPersonajes(){
       return characterDao.getCharacters();

    }
    @RequestMapping(value = "api/personajes/{id}", method = RequestMethod.POST)
    public void crearPersonajes(Personajes character){
        characterDao.saveCharacter(character);

    }

    @RequestMapping(value = "api/personajes/{id}", method = RequestMethod.PUT)
    public void editarPersonajes(Personajes character){
         characterDao.updateCharacters(character);

    }

    @RequestMapping(value = "api/personajes/{id}", method = RequestMethod.DELETE)
    public void eliminarPersonajes(@PathVariable Long id){
        characterDao.deleteCharacters(id);
    }





}
