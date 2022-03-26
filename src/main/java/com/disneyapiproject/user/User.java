package com.disneyapiproject.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;




}
