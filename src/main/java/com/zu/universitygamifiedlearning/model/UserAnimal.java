package com.zu.universitygamifiedlearning.model;

import jakarta.persistence.*;
@Entity
@Table(name = "user_animals")
public class UserAnimal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "animal_type_id", nullable = false)
    private AnimalType animalType; // 当前动物等级与外观

    private int currentExperience = 0; // 当前经验值

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }
}

