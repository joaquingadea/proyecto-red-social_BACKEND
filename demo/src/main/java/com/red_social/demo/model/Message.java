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
    private boolean edited;

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
        this.edited = false;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }
}
