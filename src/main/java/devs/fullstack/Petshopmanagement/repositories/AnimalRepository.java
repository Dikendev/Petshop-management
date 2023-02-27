package devs.fullstack.Petshopmanagement.repositories;
import devs.fullstack.Petshopmanagement.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
