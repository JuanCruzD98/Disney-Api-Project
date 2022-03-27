package com.disneyapiproject.characters;

import com.disneyapiproject.movie.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "image")
    private String image;


    @Column(name = "name")
    private String name;


    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "history")
    private String history;

    @ManyToMany
    @JoinTable(name = "character_movie",
            joinColumns = @JoinColumn(name = "characters_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movies_id",referencedColumnName = "id"))
    private Set<Movie> movies = new HashSet<>();
}
