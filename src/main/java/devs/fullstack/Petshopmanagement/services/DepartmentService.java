package devs.fullstack.Petshopmanagement.services;
import devs.fullstack.Petshopmanagement.models.Department;
import devs.fullstack.Petshopmanagement.repositories.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public  void deleteDepartment(Department department) {
        departmentRepository.delete(department);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }
}
