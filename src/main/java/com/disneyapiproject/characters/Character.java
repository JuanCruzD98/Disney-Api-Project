package com.disneyapiproject.characters;

import com.disneyapiproject.movie.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
@Table
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter @Column( name = "image")
    private String image;

    @Getter @Setter @Column( name = "name")
    private String name;

    @Getter @Setter @Column( name = "age")
    private Integer age;

    @Getter @Setter @Column( name = "weight")
    private Integer weight;

    @Getter @Setter @Column( name = "history")
    private String history;
    @JoinTable(name = "character_movie",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @ManyToMany
    private List<Movie> movies ;

}
