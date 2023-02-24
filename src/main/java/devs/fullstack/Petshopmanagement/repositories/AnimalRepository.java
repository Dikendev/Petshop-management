package devs.fullstack.Petshopmanagement.repositories;

import devs.fullstack.Petshopmanagement.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<List<Animal>> findByName(String name);
}
