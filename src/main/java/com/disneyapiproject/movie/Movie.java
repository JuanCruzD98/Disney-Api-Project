package com.disneyapiproject.movie;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "peliculas")
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

    @Getter @Setter @Column( name = "dateOfCreation")
    private Date date;
    @Getter @Setter @Column( name = "rating")
    private Integer rating;

}
