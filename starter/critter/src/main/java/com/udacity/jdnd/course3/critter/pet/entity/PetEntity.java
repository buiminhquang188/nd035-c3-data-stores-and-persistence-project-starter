package com.udacity.jdnd.course3.critter.pet.entity;

import com.udacity.jdnd.course3.critter.common.enums.GenderType;
import com.udacity.jdnd.course3.critter.schedule.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "pet")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Byte age;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    private PetTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "pet")
    private List<ScheduleEntity> schedules;
}
