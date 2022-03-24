package com.disneyapiproject.characters;





import java.util.List;

public interface ICharacterService {

    List<Character> getAllCharacters();
    Character saveCharacter(Character character);
    Character getCharacterById(Long id);
    Character updateCharacter(Character character,Long id);
    void deleteCharacter(Long id);
    List<Character> findByName(String name);
    List <Character> findByAge(Integer age);


}
