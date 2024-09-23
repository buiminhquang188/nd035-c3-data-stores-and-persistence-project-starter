package com.udacity.jdnd.course3.critter.user.entity;

import com.udacity.jdnd.course3.critter.pet.entity.PetEntity;
import com.udacity.jdnd.course3.critter.schedule.entity.ScheduleEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "user")
    private List<UserSkillEntity> userSkills;

    @OneToMany(mappedBy = "user")
    private List<PetEntity> pets;

    @OneToMany(mappedBy = "user")
    private List<ScheduleEntity> schedules;
}
