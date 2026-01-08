package com.red_social.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate creationDate;


    // FK user_id
    // 1 a 1 -> profile -> user
    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserSec user;

    public Profile(){
    }

    public Profile(LocalDate creationDate, UserSec user) {
        this.creationDate = creationDate;
        this.user = user;
    }
}
