package com.disneyapiproject.dao;

import com.disneyapiproject.models.Personajes;

import java.util.List;

public interface CharacterDao {

   List<Personajes> getCharacters();
   void deleteCharacters(Long id);
   void updateCharacters(Personajes character);
   void saveCharacter(Personajes character);
}
