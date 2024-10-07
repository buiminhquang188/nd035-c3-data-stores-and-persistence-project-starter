package com.udacity.jdnd.course3.critter.schedule.mapper;

import com.udacity.jdnd.course3.critter.pet.entity.PetEntity;
import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.user.entity.SkillEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper {
    public ScheduleDTO entityToDTO(ScheduleEntity scheduleEntity) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(scheduleEntity.getId());
        scheduleDTO.setDate(scheduleEntity.getDate()
                .toLocalDate());
        scheduleDTO.setEmployeeIds(
                scheduleEntity.getUsers()
                        .stream()
                        .map(UserEntity::getId)
                        .collect(Collectors.toList())
        );
        scheduleDTO.setPetIds(
                scheduleEntity.getPets()
                        .stream()
                        .map(PetEntity::getId)
                        .collect(Collectors.toList())
        );
        scheduleDTO.setActivities(
                scheduleEntity.getSkills()
                        .stream()
                        .map(SkillEntity::getName)
                        .collect(Collectors.toSet())
        );
        return scheduleDTO;
    }

    public List<ScheduleDTO> entitiesToDTOs(List<ScheduleEntity> scheduleEntity) {
        return scheduleEntity.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
}
