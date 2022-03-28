package com.disneyapiproject.movie;

import com.disneyapiproject.mapstruct.dto.GenreSlimDto;
import com.disneyapiproject.mapstruct.dto.ListOfLongDto;
import com.disneyapiproject.mapstruct.dto.MovieDto;
import com.disneyapiproject.mapstruct.dto.MovieSlimDto;
import com.disneyapiproject.mapstruct.mappers.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/movies")
public class MovieController {

    @Autowired
    private final MapStructMapper mapStructMapper;
    @Autowired
    private MovieServiceImpl movieServiceImpl;


    @PostMapping
    private ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movie){
        Movie movieCreated = movieServiceImpl.saveMovie(mapStructMapper.movieDtoToMovie(movie));
        return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieCreated), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<MovieSlimDto>> getAllMovies() {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieSlimDtos(movieServiceImpl.getAllMovies()), HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("id") Long movieId){
       return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieServiceImpl.getMovieById(movieId)), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable("id") Long id, @RequestBody MovieDto movie){
        Movie movieUpdated = movieServiceImpl.saveMovie(mapStructMapper.updateMovieFromDto(movie, movieServiceImpl.getMovieById(id)));

        return new ResponseEntity<>(mapStructMapper.movieToMovieDto(movieUpdated), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletemovie(@PathVariable("id") Long id){
        movieServiceImpl.deleteMovie(id);
        return new ResponseEntity<String>("Movie deleted succesfully", HttpStatus.OK);
    }

    @GetMapping(params="title")
    public ResponseEntity<List<MovieDto>> findMovieByTitle(@RequestParam(value = "title", required = false) String title) {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieDtos(movieServiceImpl.findByTitle(title)), HttpStatus.OK);

    }

    @GetMapping(params = "order")
    public List<Movie> getAllMoviesByDateOrder(@RequestParam(value = "order") String order){
        return movieServiceImpl.findByDateOrder(order);
    }

    @GetMapping(params="genre")
    public ResponseEntity<List<MovieDto>> findMovieByGenre(@RequestParam(value = "genre", required = false) Long genreId) {

        return new ResponseEntity<>(mapStructMapper.moviesToMovieDtos(movieServiceImpl.findByGenreId(genreId)), HttpStatus.OK);

    }

    @DeleteMapping("{id}/genres")
    public ResponseEntity<?> removeGenresFromMovie(@Valid @RequestBody ListOfLongDto genresIds, @PathVariable("id") Long movieId) {

        movieServiceImpl.removeGenres(movieId, genresIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @GetMapping("{id}/genres")
    public ResponseEntity<List<GenreSlimDto>> getMovieGenres(@PathVariable("id") Long movieId) {

        return new ResponseEntity<>(mapStructMapper.genresToGenreSlimDtos(new ArrayList<>(movieServiceImpl.getGenres(movieId))), HttpStatus.OK);

    }

    @PutMapping("{id}/genres")
    public ResponseEntity<?> addGenresToMovie(@Valid @RequestBody ListOfLongDto genresIds, @PathVariable("id") Long movieId) {

        movieServiceImpl.addGenres(movieId, genresIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
