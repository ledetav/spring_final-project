package com.lessons.final_project.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key
    private String name;
    private String gender; // "male" or "female"
    private boolean neutered; // "true" or "false"

    @ManyToOne
    private Breed breed;
    
    private boolean purebred; // "true" or "false"
    private double weight;
    private LocalDate birthDate; // "yyyy-mm-dd"

    public Cat(String name, String gender, boolean neutered, Breed breed, boolean purebred, double weight, LocalDate birthDate) {
        this.name = name;
        this.gender = gender;
        this.neutered = neutered;
        this.breed = breed;
        this.purebred = purebred;
        this.weight = weight;
        this.birthDate = birthDate;
    }

    public Cat() {
    }

    // Getters and setters

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public boolean isPurebred() {
        return purebred;
    }

    public void setPurebred(boolean purebred) {
        this.purebred = purebred;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
