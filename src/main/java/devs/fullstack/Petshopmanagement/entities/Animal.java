package devs.fullstack.Petshopmanagement.entities;

import devs.fullstack.Petshopmanagement.TreatType;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_animals")
public class Animal {

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

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "breed")
    private String breed;

    @Column(name = "weight")
    private double weight;

    @Column(name = "hair_type")
    private String hairType;

    @Enumerated(EnumType.STRING)
    @Column(name = "treat_type")
    private TreatType treatType;

    public Animal() {}

    // getters and setters for the attributes
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public TreatType getTreatType() {
        return treatType;
    }

    public void setTreatType(TreatType treatType) {
        this.treatType = treatType;
    }
}