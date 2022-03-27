package com.disneyapiproject.characters;

import com.disneyapiproject.exceptions.ResourceNotFoundException;

import com.disneyapiproject.movie.Movie;
import com.disneyapiproject.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CharacterServiceImpl implements ICharacterService {

    private final MovieRepository movieRepository;
    private CharacterRepository characterRepository;



    @Override
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public Character getCharacterById(Long id) {
        return characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character","Id",id));
    }

    @Override
    public Character updateCharacter(Character character, Long id) {
        //Check if character already exist in DB
        Character existingCharacter = characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character", "Id", id));

        existingCharacter.setName(character.getName());
        existingCharacter.setAge(character.getAge());
        existingCharacter.setImage(character.getImage());
        existingCharacter.setWeight(character.getWeight());
        existingCharacter.setHistory((character.getHistory()));

        characterRepository.save(existingCharacter);
        return existingCharacter;

    }
    @Override
    public void deleteCharacter(Long id){
       //Check existence in DB

        characterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Character", "Id", id));

        characterRepository.deleteById(id);
    }



    @Override
    public List<Character> findByName(String name) {
        return characterRepository.findByName(name);
    }

    @Override
    public List<Character> findByAge(Integer age) {
        return characterRepository.findByAge(age);
    }

    @Override
    public List<Character> findByMovieId(Long movieId) {
        return characterRepository.findByMoviesId(movieId);
    }
    private boolean checkIfMovieExist(List<Long> moviesIds) {

        return movieRepository.findAll().stream().map(Movie::getId).collect(Collectors.toList()).containsAll(moviesIds);

    }

    @Override
    public void addMovies(Long characterId, List<Long> moviesIds) {

        Character character = getCharacterById(characterId);

        if (checkIfMovieExist(moviesIds)) {

            movieRepository.findAllById(moviesIds).forEach(movie -> character.getMovies().add(movie));

        } else {

            throw new ResourceNotFoundException("Movie", "Id", moviesIds);

        }

        characterRepository.save(character);

    }

    @Override
    public void removeMovies(Long characterId, List<Long> moviesIds) {

        Character character = getCharacterById(characterId);

        character.getMovies().removeIf(movie -> moviesIds.contains(movie.getId()));

        characterRepository.save(character);

    }


}
