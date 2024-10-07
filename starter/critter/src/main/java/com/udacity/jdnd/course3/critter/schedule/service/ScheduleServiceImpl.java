package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.common.exception.runtime.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.entity.PetEntity;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.schedule.mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.entity.SkillEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;
import com.udacity.jdnd.course3.critter.user.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.enums.RoleType;
import com.udacity.jdnd.course3.critter.user.repository.SkillRepository;
import com.udacity.jdnd.course3.critter.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final PetRepository petRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(UserRepository userRepository, SkillRepository skillRepository, PetRepository petRepository, ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.petRepository = petRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Transactional
    @Override
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setDate(scheduleDTO.getDate()
                .atStartOfDay());

        List<UserEntity> users = this.userRepository.findAllByIdInAndRoleId(scheduleDTO.getEmployeeIds(), RoleType.EMPLOYEE.getValue());
        users.stream()
                .filter(Objects::nonNull)
                .forEach(schedule::addUser);

        List<PetEntity> pets = this.petRepository.findAllById(scheduleDTO.getPetIds());
        pets.stream()
                .filter(Objects::nonNull)
                .forEach(schedule::addPet);

        List<SkillEntity> skills = this.skillRepository.findAllById(scheduleDTO.getActivities()
                .stream()
                .map(EmployeeSkill::getId)
                .collect(Collectors.toList()));
        skills.stream()
                .filter(Objects::nonNull)
                .forEach(schedule::addSkill);

        ScheduleEntity savedSchedule = this.scheduleRepository.save(schedule);
        return this.scheduleMapper.entityToDTO(savedSchedule);
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleEntity> schedules = this.scheduleRepository.findAll();
        return this.scheduleMapper.entitiesToDTOs(schedules);
    }

    @Override
    public List<ScheduleDTO> getScheduleForPet(long petId) {
        List<ScheduleEntity> schedules = this.scheduleRepository.findByPetsId(petId);
        return this.scheduleMapper.entitiesToDTOs(schedules);
    }

    @Override
    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        List<ScheduleEntity> schedules = this.scheduleRepository.findByUsersId(employeeId);
        return this.scheduleMapper.entitiesToDTOs(schedules);
    }

    @Override
    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        UserEntity users = this.userRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<Long> petIds = users.getPets()
                .stream()
                .map(PetEntity::getId)
                .collect(Collectors.toList());

        List<ScheduleEntity> schedules = this.scheduleRepository.findByPetsIdIn(petIds);

        return this.scheduleMapper.entitiesToDTOs(schedules);
    }
}
