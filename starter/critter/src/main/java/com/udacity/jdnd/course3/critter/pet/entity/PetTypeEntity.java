package com.udacity.jdnd.course3.critter.pet.entity;

import com.udacity.jdnd.course3.critter.pet.enums.PetType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "pet_type")
public class PetTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", unique = true)
    @Enumerated(EnumType.STRING)
    private PetType type;

    @OneToMany(mappedBy = "type")
    private List<PetEntity> pets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }
}
