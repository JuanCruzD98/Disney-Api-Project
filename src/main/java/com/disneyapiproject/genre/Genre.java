package com.disneyapiproject.genre;

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
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column( name = "id")
    private Long id;
    @Getter @Setter @Column( name = "name")
    private String name;
    @Getter @Setter @Column( name = "image")
    private String image;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies = new ArrayList<>();
}
