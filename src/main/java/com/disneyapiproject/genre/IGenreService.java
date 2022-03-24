package com.disneyapiproject.genre;


public interface IGenreService {

    public Genre saveGenre(Genre genre);
    public void deleteGenre(Long id);
    public Genre getGenreById(Long id);
    public Genre updateGenre(Genre genre, Long id);


}
