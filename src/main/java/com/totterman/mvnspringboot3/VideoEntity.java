package com.totterman.mvnspringboot3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class VideoEntity {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private String username;

    protected VideoEntity() {
        this(null, null, null);
    }

    public VideoEntity(String name, String description, String username) {
        this.name = name;
        this.description = description;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "VideoEntity [id=" + id + ", name=" + name + ", description=" + description + ", username=" + username
                + "]";
    }
    
}
