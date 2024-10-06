package com.udacity.jdnd.course3.critter.user.repository;

import com.udacity.jdnd.course3.critter.user.entity.UserOperationTimeEntity;
import com.udacity.jdnd.course3.critter.user.entity.key.IdUserOperationTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOperationTimeRepository extends JpaRepository<UserOperationTimeEntity, IdUserOperationTime> {
    List<UserOperationTimeEntity> findByOperationTimeId(Integer operationTimeId);
}