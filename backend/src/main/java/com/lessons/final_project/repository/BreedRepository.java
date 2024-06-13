package com.lessons.final_project.repository;

// BreedRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.lessons.final_project.model.Breed;

public interface BreedRepository extends JpaRepository<Breed, Long> {}