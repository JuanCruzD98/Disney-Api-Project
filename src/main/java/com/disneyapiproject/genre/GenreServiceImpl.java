package com.disneyapiproject.genre;

import com.disneyapiproject.exceptions.ResourceNotFoundException;
import com.disneyapiproject.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GenreServiceImpl implements IGenreService {

    private GenreRepository genreRepository;


    public Genre saveGenre(Genre genre){
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
       //Checks existance in DB
        genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre", "Id", id));
        genreRepository.deleteById(id);

    }




    public Genre getGenreById(Long id){
        return genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre", "Id", id));
    }


    public Genre updateGenre(Genre genre, Long id){
        //Check if movie already exist in DB
        Genre existingGenre = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre", "Id", id));

        existingGenre.setName(genre.getName());
        existingGenre.setImage(genre.getImage());


        genreRepository.save(existingGenre);
        return existingGenre;
    }

}
