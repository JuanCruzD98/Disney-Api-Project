package com.disneyapiproject.characters;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CharacterRepository extends JpaRepository<Character,Long> {

    List<Character> findByName(String name);

    List<Character> findByAge(Integer age);

    Optional<Character> findById(Long id);

    List<Character> findByMoviesId(Long id);
}
