package devs.fullstack.Petshopmanagement.repositories;

import devs.fullstack.Petshopmanagement.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> { }
