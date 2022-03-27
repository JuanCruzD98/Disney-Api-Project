package com.disneyapiproject.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name", nullable = false)
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name ="email")
    private String email;




}
