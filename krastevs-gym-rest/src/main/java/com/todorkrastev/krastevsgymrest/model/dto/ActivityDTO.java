package com.todorkrastev.krastevsgymrest.model.dto;

public class ActivityDTO {
    private Long id;
    private String title;
    private String description;
    private String imageURL;

    public ActivityDTO() {
    }

    public Long getId() {
        return id;
    }

    public ActivityDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ActivityDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActivityDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ActivityDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }
}
