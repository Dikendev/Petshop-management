package devs.fullstack.Petshopmanagement.controllers;

import devs.fullstack.Petshopmanagement.models.DepartmentModel;
import devs.fullstack.Petshopmanagement.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentModel>> findAll() {
        return  ResponseEntity.ok().body(departmentService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<DepartmentModel> optionalDepartmentModel = departmentService.findById(id);
        if (optionalDepartmentModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no departments with id" + id);
        } else {
            return ResponseEntity.ok().body(optionalDepartmentModel.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> addDepartment(@RequestBody @Valid DepartmentModel departmentModel) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(departmentModel));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable Long id) {
        Optional<DepartmentModel> optionalDepartmentModel = departmentService.findById(id);
        if(optionalDepartmentModel.isPresent()) {
            departmentService.deleteDepartment(optionalDepartmentModel.get());
            return ResponseEntity.ok().body("Deleted with success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No id departments found " + id);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateDepartment(@PathVariable Long id, @RequestBody DepartmentModel departmentModel) {
        Optional<DepartmentModel> optionalDepartmentModel = departmentService.findById(id);
        if (optionalDepartmentModel.isPresent()) {
            DepartmentModel newDepartment = optionalDepartmentModel.get();
            BeanUtils.copyProperties(departmentModel, newDepartment);
            return ResponseEntity.ok().body(departmentService.addDepartment(newDepartment));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No id departments found " + id);
        }
    }
}
