package com.disneyapiproject.genre;

import com.disneyapiproject.movie.Movie;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import java.util.HashSet;

import java.util.Set;


@Getter
@Setter
@Entity
@Table(name ="genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id", nullable = false)
    private Long id;
    @Column( name = "name")
    private String name;
    @Column( name = "image")
    private String image;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();
}
