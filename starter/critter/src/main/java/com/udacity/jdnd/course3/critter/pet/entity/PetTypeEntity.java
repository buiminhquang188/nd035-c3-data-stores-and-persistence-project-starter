package com.udacity.jdnd.course3.critter.pet.entity;

import com.udacity.jdnd.course3.critter.pet.enums.PetType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "pet_type")
public class PetTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PetType type;

    @OneToMany(mappedBy = "type")
    private List<PetEntity> pets;
}
