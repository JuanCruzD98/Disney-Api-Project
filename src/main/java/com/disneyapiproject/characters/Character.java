package com.disneyapiproject.characters;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "personajes")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter @Column( name = "imagen")
    private String image;

    @Getter @Setter @Column( name = "nombre")
    private String name;

    @Getter @Setter @Column( name = "edad")
    private Integer age;

    @Getter @Setter @Column( name = "peso")
    private Integer weight;

    @Getter @Setter @Column( name = "historia")
    private String history;


}
