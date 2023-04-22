package devs.fullstack.Petshopmanagement.services;
import devs.fullstack.Petshopmanagement.repositories.AnimalRepository;
import devs.fullstack.Petshopmanagement.models.Animal;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Transactional
    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Transactional
    public void deleteAnimal(Animal animal) {
        animalRepository.delete(animal);
    }

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Optional<Animal> findById(Long id){
        return animalRepository.findById(id);
    }
}
