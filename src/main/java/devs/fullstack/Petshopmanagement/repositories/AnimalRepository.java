package devs.fullstack.Petshopmanagement.repositories;
import devs.fullstack.Petshopmanagement.models.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<AnimalModel, Long> {
}
