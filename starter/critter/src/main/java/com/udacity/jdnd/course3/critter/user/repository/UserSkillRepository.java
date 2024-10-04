package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.entity.UserSkillEntity;
import com.udacity.jdnd.course3.critter.user.entity.key.IdUserSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillRepository extends JpaRepository<UserSkillEntity, IdUserSkill> {
}