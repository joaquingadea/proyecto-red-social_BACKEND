package com.red_social.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate creationDate;


    // FK user_id
    // 1 a 1 -> profile <-> user
    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,unique = true)
    private UserSec user;

    public Profile(){
    }

    public Profile(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setUser(UserSec user) {
        this.user = user;
    }

    public UserSec getUser() {
        return user;
    }
}
