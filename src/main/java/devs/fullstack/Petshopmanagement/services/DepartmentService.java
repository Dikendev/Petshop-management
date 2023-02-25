package devs.fullstack.Petshopmanagement.services;


import devs.fullstack.Petshopmanagement.models.DepartmentModel;
import devs.fullstack.Petshopmanagement.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentModel> findAll() {
        return departmentRepository.findAll();
    }

    public DepartmentModel addDepartment(DepartmentModel department) {
        return departmentRepository.save(department);
    }
}
