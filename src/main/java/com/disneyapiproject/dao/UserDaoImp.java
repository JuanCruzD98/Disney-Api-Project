package com.disneyapiproject.dao;

import com.disneyapiproject.models.Usuarios;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuarios> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public void eliminar(Long id) {
        Usuarios usuario = entityManager.find(Usuarios.class, id);

        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuarios user) {
        entityManager.merge(user);

    }

    public Usuarios obtenerCredenciales(Usuarios usuario) {
        String query = "FROM Usuarios WHERE email = :email";
        List<Usuarios> list = entityManager.createQuery(query).setParameter("email", usuario.getEmail()).getResultList();

        if (list.isEmpty()) {
            return null;
        }

        String passwordHashed = list.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return list.get(0);
        }
        return null;
    }


    }



