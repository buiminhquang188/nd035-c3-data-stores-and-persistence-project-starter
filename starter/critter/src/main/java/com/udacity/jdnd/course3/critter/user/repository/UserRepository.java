package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.entity.UserEntity;
import com.udacity.jdnd.course3.critter.user.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPetsId(Long petId);

    @Query("SELECT u FROM user u " +
           "LEFT JOIN user_operation_time uot ON uot.idUserOperationTime.userId = u.id " +
           "LEFT JOIN operation_time ot ON ot.id = uot.idUserOperationTime.operationTimeId " +
           "LEFT JOIN user_skill uk ON uk.id.userId = u.id " +
           "LEFT JOIN skill s ON s.id = uk.id.skillId " +
           "WHERE uot.operationTime.dayOfWeek = :dayOfWeek " +
           "AND s.name IN :employeeSkills " +
           "AND u.role.name = :roleType")
    List<UserEntity> findAllEmployeeAvailability(DayOfWeek dayOfWeek, Set<EmployeeSkill> employeeSkills, RoleType roleType);

    List<UserEntity> findAllByIdInAndRoleId(List<Long> ids, Integer roleId);
}