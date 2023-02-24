package devs.fullstack.Petshopmanagement.entities;

import devs.fullstack.Petshopmanagement.enums.DepartmentType;
import devs.fullstack.Petshopmanagement.enums.TreatType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "department_type", nullable = false)
    private DepartmentType departmentType;

    // method without arguments
    public Department() {
    }
}
