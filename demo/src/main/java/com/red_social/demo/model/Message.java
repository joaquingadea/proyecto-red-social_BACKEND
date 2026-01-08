package com.red_social.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private LocalDateTime creationDate;

    // FK profile_id
    // N -> 1 unidireccional
    // mensajes -> profile
    // cada mensaje conoce su creador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    public Message() {
    }

    public Message(String title, String text, LocalDateTime creationDate, Profile profile) {
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        this.profile = profile;
    }

}
