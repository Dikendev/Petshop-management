package devs.fullstack.Petshopmanagement.models;
import devs.fullstack.Petshopmanagement.enums.DepartmentType;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_department")
public class DepartmentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "department_type", nullable = false)
    private DepartmentType departmentType;

    // method without arguments
    public DepartmentModel() {
    }
}
