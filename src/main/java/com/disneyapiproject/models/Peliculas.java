package com.disneyapiproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "peliculas")

public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter @Column( name = "imagen")
    private String imagen;

    @Getter @Setter @Column( name = "titulo")
    private String titulo;

    @Getter @Setter @Column( name = "fecha_de_creacion")
    private Integer fecha_de_creacion;

    @Getter @Setter @Column( name = "calificacion")
    private Integer calificacion;




}
