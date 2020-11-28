package com.mpls.mainservice.dto.command;

public class CountryCommandDto {
    private Long id;
    private String name;
    private String color;
    private String photo;

    public Long getId() {
        return id;
    }

    public CountryCommandDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryCommandDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CountryCommandDto setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CountryCommandDto setPhoto(String photo) {
        this.photo = photo;
        return this;
    }
}
