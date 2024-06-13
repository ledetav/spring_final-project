package com.lessons.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lessons.final_project.model.Cat;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByBreedId(Long breedId);
    void deleteByBreedId(Long breedId);
}
