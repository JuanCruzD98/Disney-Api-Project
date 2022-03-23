package com.disneyapiproject.characters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/characters")
public class CharacterController {

    @Autowired
    private CharacterServiceImpl characterServiceImpl;


    @PostMapping()
    public ResponseEntity<Character> saveCharacter(@RequestBody Character character){
        return new ResponseEntity<Character>(characterServiceImpl.saveCharacter(character), HttpStatus.CREATED);
    }


    @GetMapping()
    public List<Character> getAllCharacters(){
        return characterServiceImpl.getAllCharacters();
    }

    @GetMapping("{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable("id") Long characterId){
        return new ResponseEntity<Character>(characterServiceImpl.getCharacterById(characterId), HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable("id") Long id, @RequestBody Character character){
        return new ResponseEntity<Character>(characterServiceImpl.updateCharacter(character,id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deteleCharacter(@PathVariable("id") Long id){
        characterServiceImpl.deleteCharacter(id);
        return new ResponseEntity<String>("Character deleted succesfully", HttpStatus.OK);

    }

    @GetMapping(params ="name")
    public List<Character> findCharacterByName(@RequestParam(value = "name") String name){
        return characterServiceImpl.findByName(name);

    }

    @GetMapping(params="age")
    public List<Character> findCharacterByAge(@RequestParam(value ="age") Integer age){
        return characterServiceImpl.findByAge(age);

    }





}
