package com.disneyapiproject.movie;

import com.disneyapiproject.characters.Character;

import java.util.List;

public interface IMovieService {

    List<Movie> getAllMovies();
    Movie saveMovie(Movie movie);
    Movie updateMovie(Movie movie,Long id);
    void deleteMovie(Long id);
    Movie getMovieById(Long id);

    List<Movie> findByTitle(String title);
    List<Movie> findByDateOrder(String order);




}
