package com.disneyapiproject.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository {
    boolean emailExist(String email);

    Optional<User> findByEmail(String email);
}
