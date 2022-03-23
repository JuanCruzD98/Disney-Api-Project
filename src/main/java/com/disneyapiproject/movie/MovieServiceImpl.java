package com.disneyapiproject.movie;

import com.disneyapiproject.characters.Character;
import com.disneyapiproject.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MovieServiceImpl implements IMovieService{


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
}

