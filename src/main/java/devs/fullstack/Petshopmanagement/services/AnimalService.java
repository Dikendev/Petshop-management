package devs.fullstack.Petshopmanagement.services;

import devs.fullstack.Petshopmanagement.repositories.AnimalRepository;
import devs.fullstack.Petshopmanagement.entities.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }
    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }
}
