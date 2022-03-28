package com.disneyapiproject.characters;

import com.disneyapiproject.mapstruct.dto.CharacterDto;
import com.disneyapiproject.mapstruct.dto.CharacterSlimDto;
import com.disneyapiproject.mapstruct.dto.ListOfLongDto;
import com.disneyapiproject.mapstruct.dto.MovieSlimDto;
import com.disneyapiproject.mapstruct.mappers.MapStructMapper;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping(value = "api/characters")
public class CharacterController {

    @Autowired
    private final MapStructMapper mapStructMapper;
    @Autowired
    private CharacterServiceImpl characterServiceImpl;


    @PostMapping()
    public ResponseEntity<CharacterDto> saveCharacter(@Valid @RequestBody CharacterDto character) {
        Character characterCreated = characterServiceImpl.saveCharacter(mapStructMapper.characterDtoToCharacter(character));
        return new ResponseEntity<>(mapStructMapper.characterToCharacterDto(characterCreated), HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<CharacterSlimDto>> getAllCharacters(){
        return new ResponseEntity<>(mapStructMapper.charactersToCharacterSlimDtos(characterServiceImpl.getAllCharacters()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getCharacterById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(mapStructMapper.characterToCharacterDto(characterServiceImpl.getCharacterById(id)), HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<CharacterDto> updateCharacter(@Valid @RequestBody CharacterDto character, @PathVariable("id") Long id) {

        Character characterUpdated = characterServiceImpl.saveCharacter(mapStructMapper.updateCharacterFromDto(character, characterServiceImpl.getCharacterById(id)));

        return new ResponseEntity<>(mapStructMapper.characterToCharacterDto(characterUpdated), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharacterById(@PathVariable("id") Long id) {

        characterServiceImpl.deleteCharacter(id);

        return new ResponseEntity<String>("Character deleted succesfully",HttpStatus.OK);

    }

    @GetMapping(params = "name")
    public ResponseEntity<List<CharacterDto>> findCharacterByName(@RequestParam(value = "name", required = false) String name)  {

        return new ResponseEntity<>(mapStructMapper.charactersToCharacterDtos(characterServiceImpl.findByName(name)), HttpStatus.OK);

    }


    @GetMapping(params="age")
    public ResponseEntity<List<CharacterDto>> findCharacterByAge( @RequestParam(value = "age", required = false) Integer age) {

        return new ResponseEntity<>(mapStructMapper.charactersToCharacterDtos(characterServiceImpl.findByAge(age)), HttpStatus.OK);

    }

    @GetMapping(params="movie")
    public ResponseEntity<List<CharacterDto>> findCharacterByMovieId(@RequestParam(value = "movie", required = false) Long movieId) {

        return new ResponseEntity<>(mapStructMapper.charactersToCharacterDtos(characterServiceImpl.findByMovieId(movieId)), HttpStatus.OK);

    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<List<MovieSlimDto>> getCharacterMovies(@PathVariable("id") Long characterId) {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieSlimDtos(new ArrayList<>(characterServiceImpl.getCharacterById(characterId).getMovies())), HttpStatus.OK);


    }

    @PutMapping("/{id}/movies")
    public ResponseEntity<?> addMoviesToCharacter(@Valid @RequestBody ListOfLongDto moviesIds, @PathVariable("id") Long characterId) {

        characterServiceImpl.addMovies(characterId, moviesIds.getList());

        return new ResponseEntity<>(HttpStatus.OK);

    }







}
