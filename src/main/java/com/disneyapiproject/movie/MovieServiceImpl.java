package com.disneyapiproject.movie;

import com.disneyapiproject.characters.Character;
import com.disneyapiproject.exceptions.ResourceNotFoundException;
import com.disneyapiproject.genre.Genre;
import com.disneyapiproject.genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class MovieServiceImpl implements IMovieService{

    private final GenreRepository genreRepository;
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie, Long id) {
        //Check if movie already exist in DB
       Movie existingMovie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", id));

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDate(movie.getDate());
        existingMovie.setImage(movie.getImage());
        existingMovie.setRating(movie.getRating());


        movieRepository.save(existingMovie);
        return existingMovie;

    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", id));
        movieRepository.deleteById(id);

    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie", "Id", id));
    }


    @Override
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }


    @Override
    public List<Movie> findByDateOrder(String order) {
        if(order.equals("ASC")){
            return movieRepository.findAllByOrderByDateAsc();
        }else if(order.equals("DESC")){
            return movieRepository.findAllByOrderByDateDesc();
        }else{
            return movieRepository.findAll();
        }
    }

    @Override
    public List<Movie> findByGenreId(Long idGenre) {
        return movieRepository.findByGenresId(idGenre);
    }

    @Override
    public Set<Genre> getGenres(Long id) {
        return getMovieById(id).getGenres();
    }

    @Override
    public void removeGenres(Long movieId, List<Long> genresIds) {
        Movie movie = getMovieById(movieId);

        movie.getGenres().removeIf(genre -> genresIds.contains(genre.getId()));

        movieRepository.save(movie);
    }

    private boolean checkIfGenreExist(List<Long> genresIds) {

        return genreRepository.findAll().stream().map(Genre::getId).collect(Collectors.toList()).containsAll(genresIds);

    }

    @Override
    public void addGenres(Long movieId, List<Long> genresIds) {
        Movie movie = getMovieById(movieId);

        if (checkIfGenreExist(genresIds)) {

            genreRepository.findAllById(genresIds).forEach(genre -> movie.getGenres().add(genre));

        } else {

            throw new ResourceNotFoundException("Movie", "Id", movieId);

        }

        movieRepository.save(movie);
    }




}

