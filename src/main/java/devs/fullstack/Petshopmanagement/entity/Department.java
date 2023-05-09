package devs.fullstack.Petshopmanagement.entity;
import devs.fullstack.Petshopmanagement.entity.model.BaseEntity;
import devs.fullstack.Petshopmanagement.enums.DepartmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_department")
@EqualsAndHashCode(callSuper = false)
public class Department extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "department_type", nullable = false)
    private DepartmentType departmentType;

}
