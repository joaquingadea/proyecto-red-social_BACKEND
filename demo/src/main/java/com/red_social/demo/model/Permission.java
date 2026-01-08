package com.red_social.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Permission() {
    }

    public Permission(String name) {
        this.name = name;
    }
}
