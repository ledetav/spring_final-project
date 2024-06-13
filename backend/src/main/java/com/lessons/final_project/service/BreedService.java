package com.lessons.final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lessons.final_project.model.Breed;
import com.lessons.final_project.model.Cat;
import com.lessons.final_project.repository.BreedRepository;
import com.lessons.final_project.repository.CatRepository;

import java.util.List;

@Service
public class BreedService {
    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private CatRepository catRepository; // Внедряем CatRepository

    public List<Breed> findAll() {
        return breedRepository.findAll();
    }

    public Breed findById(Long id) {
        return breedRepository.findById(id).orElse(null);
    }

    public Breed save(Breed breed) {
        return breedRepository.save(breed);
    }

    public void deleteById(Long id) {
        breedRepository.deleteById(id);
    }
    
    public List<Cat> findCatsByBreedId(Long breedId) {
        return catRepository.findByBreedId(breedId);
    }
}
