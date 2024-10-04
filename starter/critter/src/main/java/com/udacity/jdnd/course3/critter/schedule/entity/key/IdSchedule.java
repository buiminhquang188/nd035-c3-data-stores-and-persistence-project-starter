package com.udacity.jdnd.course3.critter.schedule.entity.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdSchedule implements Serializable {
    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "owner_id")
    private Long ownerId;
}
