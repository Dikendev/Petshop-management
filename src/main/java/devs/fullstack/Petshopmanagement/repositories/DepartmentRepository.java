package devs.fullstack.Petshopmanagement.repositories;
import devs.fullstack.Petshopmanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> { }
