package com.disneyapiproject.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitle(String title);

   // List<Movie> findByGenresId(Long genreId);

    List<Movie> findAllByOrderByDateAsc();

    List<Movie> findAllByOrderByDateDesc();
}
