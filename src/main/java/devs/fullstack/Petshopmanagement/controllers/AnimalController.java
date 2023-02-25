package devs.fullstack.Petshopmanagement.controllers;

import devs.fullstack.Petshopmanagement.models.AnimalModel;
import devs.fullstack.Petshopmanagement.services.AnimalService;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping()
    public ResponseEntity<List<AnimalModel>> getAllAnimals() {
        List<AnimalModel> animalModels = animalService.findAll();
        return new ResponseEntity<>(animalModels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnimalModel> addAnimal(@RequestBody AnimalModel animalModel) {
       return ResponseEntity.status(HttpStatus.CREATED).body(animalService.addAnimal(animalModel));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<AnimalModel> optionalAnimalModel = animalService.findById(id);
        if (optionalAnimalModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no animals with id" + id);
        } else {
            return ResponseEntity.ok().body(optionalAnimalModel.get());
        }
    }

}
