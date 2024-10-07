package com.udacity.jdnd.course3.critter.schedule.entity;

import com.udacity.jdnd.course3.critter.pet.entity.PetEntity;
import com.udacity.jdnd.course3.critter.user.entity.SkillEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToMany
    @JoinTable(name = "schedule_employee",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "schedule_pet",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private Set<PetEntity> pets = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "schedule_skill",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SkillEntity> skills = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Set<PetEntity> getPets() {
        return pets;
    }

    public void setPets(Set<PetEntity> pets) {
        this.pets = pets;
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
    }

    public void addUser(UserEntity user) {
        this.users.add(user);
        user.getSchedules()
                .add(this);
    }

    public void removeUser(UserEntity user) {
        this.users.remove(user);
        user.getSchedules()
                .remove(this);
    }

    public void addPet(PetEntity pet) {
        this.pets.add(pet);
        pet.getSchedules()
                .add(this);
    }

    public void removePet(PetEntity pet) {
        this.pets.remove(pet);
        pet.getSchedules()
                .remove(this);
    }

    public void addSkill(SkillEntity skill) {
        this.skills.add(skill);
        skill.getSchedules()
                .add(this);
    }

    public void removeSkill(SkillEntity skill) {
        this.skills.remove(skill);
        skill.getSchedules()
                .remove(this);
    }
}
