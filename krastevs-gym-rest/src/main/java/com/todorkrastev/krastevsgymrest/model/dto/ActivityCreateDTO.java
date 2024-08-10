package com.todorkrastev.krastevsgymrest.model.dto;


import com.todorkrastev.krastevsgymrest.validation.annotation.UniqueTitleField;
import jakarta.validation.constraints.NotBlank;

public class ActivityCreateDTO {
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @UniqueTitleField
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Image URL is mandatory")
    private String imageURL;

    public ActivityCreateDTO() {
    }

    public Long getId() {
        return id;
    }

    public ActivityCreateDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ActivityCreateDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActivityCreateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ActivityCreateDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }
}
