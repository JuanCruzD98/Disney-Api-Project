package com.disneyapiproject.dao;

import com.disneyapiproject.models.Personajes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
@Transactional
public class CharacterDaoImp implements CharacterDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Personajes> getCharacters() {
        String query = "SELECT imagen, nombre FROM Personajes ";
        return entityManager.createQuery(query).getResultList();
    }

    public void  updateCharacters(Personajes character) {
        entityManager.merge(character);
    }

    public void saveCharacter(Personajes character) {
        entityManager.persist(character);

    }


    @Override
    public void deleteCharacters(Long id) {
       Personajes character = entityManager.find(Personajes.class,id);
        entityManager.remove(character);
    }


}
