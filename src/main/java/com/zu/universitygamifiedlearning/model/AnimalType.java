package com.zu.universitygamifiedlearning.model;

import jakarta.persistence.*;

@Entity
@Table(name = "animal_types")
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 动物名称

    private int level; // 动物等级

    private int requiredExperience; // 升级所需经验值

    private String appearanceUrl; // 动物外观的 URL

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRequiredExperience() {
        return requiredExperience;
    }

    public void setRequiredExperience(int requiredExperience) {
        this.requiredExperience = requiredExperience;
    }

    public String getAppearanceUrl() {
        return appearanceUrl;
    }

    public void setAppearanceUrl(String appearanceUrl) {
        this.appearanceUrl = appearanceUrl;
    }

    // Getters and Setters
}

