package com.todorkrastev.krastevsgymrest.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageURL;

    private Instant created = Instant.now();

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public Activity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Activity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Activity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Activity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public Activity setCreated(Instant created) {
        this.created = created;
        return this;
    }
}
