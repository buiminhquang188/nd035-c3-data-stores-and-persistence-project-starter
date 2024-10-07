package com.udacity.jdnd.course3.critter.schedule.repository;

import com.udacity.jdnd.course3.critter.schedule.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.schedule.entity.key.IdSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, IdSchedule> {
    List<ScheduleEntity> findByPetsId(Long petId);

    List<ScheduleEntity> findByUsersId(Long userId);

    List<ScheduleEntity> findByPetsIdIn(List<Long> petId);
}