package devs.fullstack.Petshopmanagement.entity;
import devs.fullstack.Petshopmanagement.entity.model.BaseEntity;
import devs.fullstack.Petshopmanagement.enums.TreatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_animal")
@EqualsAndHashCode(callSuper = false)
public class Animal extends BaseEntity {

    //foreign key h2 config
    // many animals to 1 department
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "species", nullable = false)
    private String species;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "weight", nullable = false)
    private String weight;

    @Column(name = "hair_type", nullable = false)
    private String hairType;

    @Enumerated(EnumType.STRING)
    @Column(name = "treat_type", nullable = false)
    private TreatType treatType;

}