package com.lessons.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lessons.final_project.model.Breed;
import com.lessons.final_project.model.Cat;
import com.lessons.final_project.service.BreedService;
import com.lessons.final_project.service.CatService;

import java.util.List;

@RestController
@RequestMapping("/api/breeds")
public class BreedController {
    @Autowired
    private BreedService breedService;

    @Autowired
    private CatService catService;

    @GetMapping
    public List<Breed> getAllBreeds() {
        return breedService.findAll();
    }

    @GetMapping("/{id}")
    public Breed getBreedById(@PathVariable Long id) {
        return breedService.findById(id);
    }

    @GetMapping("/{breedId}/cats")
    public ResponseEntity<List<Cat>> getCatsByBreed(@PathVariable Long breedId) {
        List<Cat> cats = breedService.findCatsByBreedId(breedId);
        return ResponseEntity.ok(cats);
    }

    @PostMapping
    public Breed createBreed(@RequestBody Breed breed) {
        return breedService.save(breed);
    }

    @PutMapping("/{id}")
    public Breed updateBreed(@PathVariable Long id, @RequestBody Breed breed) {
        breed.setId(id);
        return breedService.save(breed);
    }

    @DeleteMapping("/{id}")
    public void deleteBreed(@PathVariable Long id) {
        List<Cat> cats = breedService.findCatsByBreedId(id);
        if (cats.isEmpty()) {
            breedService.deleteById(id);
        } else {
            for(Cat cat : cats){
                catService.deleteById(cat.getId());
            }
            breedService.deleteById(id);
        }
    }
}
