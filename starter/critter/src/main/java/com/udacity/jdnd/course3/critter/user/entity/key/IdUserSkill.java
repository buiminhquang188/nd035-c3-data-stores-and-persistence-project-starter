package com.udacity.jdnd.course3.critter.user.entity.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdUserSkill implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "skill_id")
    private Integer skillId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }
}
