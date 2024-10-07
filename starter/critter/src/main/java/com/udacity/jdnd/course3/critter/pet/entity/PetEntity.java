package com.udacity.jdnd.course3.critter.pet.entity;

import com.udacity.jdnd.course3.critter.common.enums.GenderType;
import com.udacity.jdnd.course3.critter.schedule.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "pet")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Byte age;

    @Column(name = "birth_date")
    private LocalDate birthDate;

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

    @ManyToMany(mappedBy = "pets")
    private Set<ScheduleEntity> schedules;

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

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PetTypeEntity getType() {
        return type;
    }

    public void setType(PetTypeEntity type) {
        this.type = type;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<ScheduleEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<ScheduleEntity> schedules) {
        this.schedules = schedules;
    }
}
