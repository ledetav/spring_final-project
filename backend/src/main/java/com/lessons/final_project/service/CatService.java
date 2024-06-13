package com.lessons.final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lessons.final_project.model.Cat;
import com.lessons.final_project.repository.CatRepository;

import java.util.List;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;

    public List<Cat> findAll() {
        return catRepository.findAll();
    }

    public Cat findById(Long id) {
        return catRepository.findById(id).orElse(null);
    }

    public Cat save(Cat cat) {
        return catRepository.save(cat);
    }

    public void deleteById(Long id) {
        catRepository.deleteById(id);
    }

    public List<Cat> findByBreedId(Long breedId) {
        return catRepository.findByBreedId(breedId);
    }

    public void deleteByBreedId(Long breedId) {
        catRepository.deleteByBreedId(breedId);
    }
}