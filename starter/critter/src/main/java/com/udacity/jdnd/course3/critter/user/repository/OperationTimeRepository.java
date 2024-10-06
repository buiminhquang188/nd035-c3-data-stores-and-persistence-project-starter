package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.entity.OperationTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

public interface OperationTimeRepository extends JpaRepository<OperationTimeEntity, Integer> {
    Set<OperationTimeEntity> findByDayOfWeekIn(Set<DayOfWeek> dayOfWeeks);

    Optional<OperationTimeEntity> findByDayOfWeek(DayOfWeek dayOfWeek);
}