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
}
