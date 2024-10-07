package com.udacity.jdnd.course3.critter.schedule.entity.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdSchedule implements Serializable {
    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "skill_id")
    private Integer skillId;

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

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
