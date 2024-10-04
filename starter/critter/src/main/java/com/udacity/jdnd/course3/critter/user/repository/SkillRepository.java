package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SkillRepository extends JpaRepository<SkillEntity, Integer> {
    Set<SkillEntity> findByIdIn(Set<Integer> ids);
}