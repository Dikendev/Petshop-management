package devs.fullstack.Petshopmanagement.controllers;

import devs.fullstack.Petshopmanagement.models.AnimalModel;
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

    @GetMapping
    public ResponseEntity<List<AnimalModel>> getAllAnimals() {
        List<AnimalModel> animalModels = animalService.findAll();
        return new ResponseEntity<>(animalModels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnimalModel> addAnimal(@RequestBody AnimalModel animalModel) {
       return ResponseEntity.status(HttpStatus.CREATED).body(animalService.addAnimal(animalModel));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAnimal(@PathVariable Long id) {
        Optional<AnimalModel> optionalAnimalModel = animalService.findById(id);
        if(optionalAnimalModel.isPresent()) {
            animalService.deleteAnimal(optionalAnimalModel.get());
            return ResponseEntity.ok().body("Deleted with success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No id animals found " + id);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateAnimal(@PathVariable Long id, @RequestBody AnimalModel animalModel) {
        Optional<AnimalModel> optionalAnimalModel = animalService.findById(id);
        if (optionalAnimalModel.isPresent()) {
            AnimalModel newAnimal = optionalAnimalModel.get();
            BeanUtils.copyProperties(animalModel, newAnimal);
            return ResponseEntity.ok().body(animalService.addAnimal(newAnimal));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No id animals found " + id);
        }
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
