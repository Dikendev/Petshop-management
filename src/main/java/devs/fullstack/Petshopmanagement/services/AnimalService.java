package devs.fullstack.Petshopmanagement.services;

import devs.fullstack.Petshopmanagement.repositories.AnimalRepository;
import devs.fullstack.Petshopmanagement.models.AnimalModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public AnimalModel addAnimal(AnimalModel animalModel) {
        return animalRepository.save(animalModel);
    }

    @Transactional
    public void deleteAnimal(AnimalModel animalModel) {
        animalRepository.delete(animalModel);
    }

    public List<AnimalModel> findAll() {
        return animalRepository.findAll();
    }

    public Optional<AnimalModel> findById(Long id){
        return animalRepository.findById(id);
    }
}
