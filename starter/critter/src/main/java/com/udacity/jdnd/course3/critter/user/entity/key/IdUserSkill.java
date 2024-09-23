package com.udacity.jdnd.course3.critter.user.entity.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdUserSkill implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "skill_id")
    private Integer skillId;
}
