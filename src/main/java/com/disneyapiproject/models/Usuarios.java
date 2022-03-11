package com.disneyapiproject.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "usuarios")

public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter @Column( name = "nombre")
    private String nombre;

    @Getter @Setter @Column( name = "apellido")
    private String apellido;

    @Getter @Setter @Column( name = "email")
    private String email;
}
