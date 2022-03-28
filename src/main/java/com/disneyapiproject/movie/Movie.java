package com.disneyapiproject.movie;


import com.disneyapiproject.characters.Character;
import com.disneyapiproject.genre.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter @Setter
@Table(name = "movies")
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id", nullable = false)
    private Long id;

    @Column( name = "title")
    private String title;

    @Column( name = "image")
    private String image;

    @Column( name = "date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime date;
    @Column( name = "rating")
    private Integer rating;




    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Character> characters = new LinkedHashSet<>();

}


