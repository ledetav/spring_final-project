package com.lessons.final_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lessons.final_project.model.Cat;
import com.lessons.final_project.service.CatService;

import java.util.List;

@RestController
@RequestMapping("/api/cats")
public class CatController {
    @Autowired
    private CatService catService;

    @GetMapping
    public List<Cat> getAllCats() {
        return catService.findAll();
    }

    @GetMapping("/{id}")
    public Cat getCatById(@PathVariable Long id) {
        return catService.findById(id);
    }

    @PostMapping
    public Cat createCat(@RequestBody Cat cat) {
        return catService.save(cat);
    }

    @PutMapping("/{id}")
    public Cat updateCat(@PathVariable Long id, @RequestBody Cat cat) {
        cat.setId(id);
        return catService.save(cat);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable Long id) {
        catService.deleteById(id);
    }
}
