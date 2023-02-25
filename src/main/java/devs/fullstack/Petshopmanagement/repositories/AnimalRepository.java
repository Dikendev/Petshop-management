package devs.fullstack.Petshopmanagement.repositories;

import devs.fullstack.Petshopmanagement.models.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<AnimalModel, Long> {
    Optional<List<AnimalModel>> findByName(String name);
}
