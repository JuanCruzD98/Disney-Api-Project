package com.disneyapiproject.movie;

import com.disneyapiproject.characters.Character;
import com.disneyapiproject.genre.Genre;

import java.util.List;
import java.util.Set;

public interface IMovieService {

    List<Movie> getAllMovies();
    Movie saveMovie(Movie movie);
    Movie updateMovie(Movie movie,Long id);
    void deleteMovie(Long id);
    Movie getMovieById(Long id);
    List<Movie> findByTitle(String title);
    List<Movie> findByDateOrder(String order);
    List<Movie> findByGenreId(Long idGenre);
    Set<Genre> getGenres(Long id);
    void removeGenres(Long movieId, List<Long> genresIds);
    void addGenres(Long movieId, List<Long> genresIds);




}
