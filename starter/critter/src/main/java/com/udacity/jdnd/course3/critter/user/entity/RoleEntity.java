package com.udacity.jdnd.course3.critter.user.entity;

import com.udacity.jdnd.course3.critter.user.enums.RoleType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleType name;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;
}
