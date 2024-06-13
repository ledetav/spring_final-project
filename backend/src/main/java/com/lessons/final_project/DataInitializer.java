package com.lessons.final_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lessons.final_project.model.Breed;
import com.lessons.final_project.model.Cat;
import com.lessons.final_project.repository.BreedRepository;
import com.lessons.final_project.repository.CatRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Проверяем, пуста ли база данных
            long catCount = catRepository.count();

            // Если в базе данных уже есть записи, выходим из метода
            if (catCount > 0) {
                return;
            }
            
            // Создаем породы
            Breed breed1 = new Breed("Cиамская");
            Breed breed2 = new Breed("Мейн-кун");
            Breed breed3 = new Breed("Британская короткошерстная");

            breedRepository.saveAll(List.of(breed1, breed2, breed3));

            // Создаем котов
            Cat cat1 = new Cat("Мурзик", "male", true, breed1, true, 4.5, LocalDate.of(2021, 5, 15));
            Cat cat2 = new Cat("Барсик", "male", false, breed1, false, 3.8, LocalDate.of(2022, 3, 22));
            Cat cat3 = new Cat("Мурка", "female", true, breed1, true, 3.2, LocalDate.of(2020, 7, 19));

            Cat cat4 = new Cat("Лео", "male", false, breed2, true, 6.0, LocalDate.of(2021, 1, 11));
            Cat cat5 = new Cat("Тигра", "female", false, breed2, false, 5.3, LocalDate.of(2022, 9, 30));
            Cat cat6 = new Cat("Снежок", "male", true, breed2, true, 5.8, LocalDate.of(2020, 4, 5));

            Cat cat7 = new Cat("Лола", "female", true, breed3, true, 4.7, LocalDate.of(2019, 12, 14));
            Cat cat8 = new Cat("Фиона", "female", false, breed3, false, 4.1, LocalDate.of(2021, 2, 20));
            Cat cat9 = new Cat("Гарфилд", "male", true, breed3, true, 5.5, LocalDate.of(2018, 6, 25));

            catRepository.saveAll(List.of(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
        };
    }
}