package devs.fullstack.Petshopmanagement.entities;

import devs.fullstack.Petshopmanagement.enums.TreatType;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_animals")
public class Animal implements Serializable {

    // Defining a column for all attributes
    // auto-increment for the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //foreign key h2 config
    // many animals to 1 department
    @ManyToOne
    @JoinColumn(name = "department_id")
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

    public Animal() {}
}