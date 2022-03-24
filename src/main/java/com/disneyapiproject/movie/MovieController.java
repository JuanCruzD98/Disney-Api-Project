package com.disneyapiproject.movie;

import com.disneyapiproject.characters.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/movies")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieServiceImpl;

    //Guardar
    @PostMapping
    private ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        return new ResponseEntity<Movie>(movieServiceImpl.saveMovie(movie), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Movie> getAllMovies(){
        return movieServiceImpl.getAllMovies();
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long movieId){
        return new ResponseEntity<Movie>(movieServiceImpl.getMovieById(movieId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie){
        return new ResponseEntity<Movie>(movieServiceImpl.updateMovie(movie,id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletemovie(@PathVariable("id") Long id){
        movieServiceImpl.deleteMovie(id);
        return new ResponseEntity<String>("Movie deleted succesfully", HttpStatus.OK);
    }
    @GetMapping(params ="title")
    public List<Movie> findMovieByTitle(@RequestParam(value = "title") String title){
        return movieServiceImpl.findByTitle(title);

    }

    @GetMapping(params = "order")
    public List<Movie> getAllMoviesByDateOrder(@RequestParam(value = "order") String order){
        return movieServiceImpl.findByDateOrder(order);
    }

 //Faltan genres Join

}
