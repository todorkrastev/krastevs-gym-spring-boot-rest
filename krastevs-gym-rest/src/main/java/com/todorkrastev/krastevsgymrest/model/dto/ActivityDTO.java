package com.todorkrastev.krastevsgymrest.model.dto;


import com.todorkrastev.krastevsgymrest.validation.annotation.UniqueTitle;
import jakarta.validation.constraints.NotBlank;

@UniqueTitle
public class ActivityDTO {
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Image URL is mandatory")
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
