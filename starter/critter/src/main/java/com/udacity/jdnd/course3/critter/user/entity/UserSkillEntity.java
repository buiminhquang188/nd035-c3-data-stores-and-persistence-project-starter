package com.udacity.jdnd.course3.critter.user.entity;

import com.udacity.jdnd.course3.critter.user.entity.key.IdUserSkill;

import javax.persistence.*;

@Entity(name = "user_skill")
public class UserSkillEntity {
    @Id
    @EmbeddedId
    private IdUserSkill id;

    @ManyToOne
    @JoinColumn(name = "skill_id", updatable = false, insertable = false)
    private SkillEntity skill;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private UserEntity user;

    public IdUserSkill getId() {
        return id;
    }

    public void setId(IdUserSkill id) {
        this.id = id;
    }

    public SkillEntity getSkill() {
        return skill;
    }

    public void setSkill(SkillEntity skill) {
        this.skill = skill;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
