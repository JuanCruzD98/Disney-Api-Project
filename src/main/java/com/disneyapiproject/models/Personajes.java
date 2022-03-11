package com.disneyapiproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "personajes")

public class Personajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter @Column( name = "imagen")
    private String imagen;

    @Getter @Setter @Column( name = "nombre")
    private String nombre;

    @Getter @Setter @Column( name = "edad")
    private Integer edad;

    @Getter @Setter @Column( name = "peso")
    private Integer peso;

    @Getter @Setter @Column( name = "historia")
    private String historia;




}
