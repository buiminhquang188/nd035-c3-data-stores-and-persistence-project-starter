package com.udacity.jdnd.course3.critter.schedule.entity;

import com.udacity.jdnd.course3.critter.pet.entity.PetEntity;
import com.udacity.jdnd.course3.critter.schedule.entity.key.IdSchedule;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "schedule")
public class ScheduleEntity {
    @Id
    @EmbeddedId
    private IdSchedule id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "pet_id", updatable = false, insertable = false)
    private PetEntity pet;

    @ManyToOne
    @JoinColumn(name = "owner_id", updatable = false, insertable = false)
    private UserEntity user;
}
