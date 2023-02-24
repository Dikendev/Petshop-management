package devs.fullstack.Petshopmanagement.controllers;

import devs.fullstack.Petshopmanagement.repositories.AnimalRepository;
import devs.fullstack.Petshopmanagement.entities.Animal;
import devs.fullstack.Petshopmanagement.services.AnimalService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping()
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animals = animalService.findAll();
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
       return ResponseEntity.status(HttpStatus.CREATED).body(animalService.addAnimal(animal));
    }
}
