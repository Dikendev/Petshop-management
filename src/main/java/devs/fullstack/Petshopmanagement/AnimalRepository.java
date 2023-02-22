package devs.fullstack.Petshopmanagement;

import devs.fullstack.Petshopmanagement.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
