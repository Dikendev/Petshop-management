package devs.fullstack.Petshopmanagement.repositories;
import devs.fullstack.Petshopmanagement.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
