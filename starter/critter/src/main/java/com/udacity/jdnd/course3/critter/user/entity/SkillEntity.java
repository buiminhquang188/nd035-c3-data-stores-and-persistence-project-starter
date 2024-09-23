package com.udacity.jdnd.course3.critter.user.entity;

import com.udacity.jdnd.course3.critter.user.enums.EmployeeSkill;

import javax.persistence.*;
import java.util.List;

@Entity(name = "skill")
public class SkillEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private EmployeeSkill name;

    @OneToMany(mappedBy = "skill")
    private List<UserSkillEntity> skillUsers;
}
