package com.disneyapiproject.dao;

import com.disneyapiproject.models.Usuarios;

import java.util.List;

public interface UserDao {

    void registrar(Usuarios user);
    void eliminar(Long id);
    List<Usuarios> getUsuarios();
    Usuarios obtenerCredenciales(Usuarios usuario);


}
