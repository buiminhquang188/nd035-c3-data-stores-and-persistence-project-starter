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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmployeeSkill getName() {
        return name;
    }

    public void setName(EmployeeSkill name) {
        this.name = name;
    }

    public List<UserSkillEntity> getSkillUsers() {
        return skillUsers;
    }

    public void setSkillUsers(List<UserSkillEntity> skillUsers) {
        this.skillUsers = skillUsers;
    }
}
