package devs.fullstack.Petshopmanagement.models;
import devs.fullstack.Petshopmanagement.enums.TreatType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_animal")
public class Animal implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // Defining a column for all attributes
    // auto-increment for the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Animal() {}

    public Animal(Long id, Department department, String name, String species, String breed, String weight, String hairType, TreatType treatType) {
        super();
        this.id = id;
        this.department = department;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.weight = weight;
        this.hairType = hairType;
        this.treatType = treatType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return  true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Animal other = (Animal) obj;
        if(id == null) {
            if (other.id != null)
                return false;
        } else if(!id.equals(other.id))
            return false;
        return true;
    }
}