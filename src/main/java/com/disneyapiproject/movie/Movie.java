package com.disneyapiproject.movie;

import com.disneyapiproject.genre.Genre;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import java.util.*;
@Getter
@Setter
@Entity
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter @Column( name = "id", nullable = false)
    private Long Id;

    @Getter @Setter @Column( name = "title")
    private String title;

    @Getter @Setter @Column( name = "image")
    private String image;

    @Getter @Setter @Column( name = "date")
    private Date date;
    @Getter @Setter @Column( name = "rating")
    private Integer rating;



    @ManyToMany(mappedBy = "movies")
    private List<Character> characters = new ArrayList<>();

    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany
    private List<Genre> genres = new ArrayList<>();

}


