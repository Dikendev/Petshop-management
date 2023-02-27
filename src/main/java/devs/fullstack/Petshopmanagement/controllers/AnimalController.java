package devs.fullstack.Petshopmanagement.controllers;

import devs.fullstack.Petshopmanagement.models.Animal;
import devs.fullstack.Petshopmanagement.services.AnimalService;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/animals")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    //retrieves all animal entities from the animal service, and returns
    //them as a list of Animal objects.
    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animal = animalService.findAll();
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
       return ResponseEntity.status(HttpStatus.CREATED).body(animalService.addAnimal(animal));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAnimal(@PathVariable Long id) {
        Optional<Animal> optionalAnimalModel = animalService.findById(id);
        if(optionalAnimalModel.isPresent()) {
            animalService.deleteAnimal(optionalAnimalModel.get());
            return ResponseEntity.ok().body("Deleted with success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No id animals found " + id);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        Optional<Animal> optionalAnimalModel = animalService.findById(id);
        if (optionalAnimalModel.isPresent()) {
            Animal newAnimal = optionalAnimalModel.get();
            BeanUtils.copyProperties(animal, newAnimal);
            return ResponseEntity.ok().body(animalService.addAnimal(newAnimal));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No id animals found " + id);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Animal> optionalAnimalModel = animalService.findById(id);
        if (optionalAnimalModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no animals with id" + id);
        } else {
            return ResponseEntity.ok().body(optionalAnimalModel.get());
        }
    }

}
