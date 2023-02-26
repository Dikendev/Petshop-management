package devs.fullstack.Petshopmanagement.services;
import devs.fullstack.Petshopmanagement.models.DepartmentModel;
import devs.fullstack.Petshopmanagement.repositories.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public DepartmentModel addDepartment(DepartmentModel departmentModel) {
        return departmentRepository.save(departmentModel);
    }

    @Transactional
    public  void deleteDepartment(DepartmentModel departmentModel) {
        departmentRepository.delete(departmentModel);
    }

    public List<DepartmentModel> findAll() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentModel> findById(Long id) {
        return departmentRepository.findById(id);
    }
}
