package com.udacity.jdnd.course3.critter.user.mapper;

import com.udacity.jdnd.course3.critter.pet.entity.PetEntity;
import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserSkillEntity;
import com.udacity.jdnd.course3.critter.user.entity.key.IdUserOperationTime;
import com.udacity.jdnd.course3.critter.user.entity.key.IdUserSkill;
import com.udacity.jdnd.course3.critter.user.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.repository.UserSkillRepository;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final UserSkillRepository userSkillRepository;

    public UserMapper(UserSkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    public CustomerDTO toCustomerDTO(UserEntity user) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(user.getId());
        customerDTO.setName(user.getName());
        customerDTO.setPhoneNumber(user.getPhoneNumber());
        customerDTO.setPetIds(this.toPetIds(user.getPets()));

        return customerDTO;
    }

    public List<CustomerDTO> toCustomerDTOs(List<UserEntity> users) {
        return users.stream()
                .map(this::toCustomerDTO)
                .collect(Collectors.toList());
    }

    public List<Long> toPetIds(List<PetEntity> pets) {
        if (pets == null) return null;

        return pets
                .stream()
                .map(PetEntity::getId)
                .collect(Collectors.toList());
    }

    public EmployeeDTO toEmployeeDTO(UserEntity user) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(user.getId());
        employeeDTO.setName(user.getName());
        employeeDTO.setSkills(user.getUserSkills()
                .stream()
                .map(UserSkillEntity::getId)
                .map(idUserSkill -> EmployeeSkill.values()[idUserSkill.getSkillId()])
                .collect(Collectors.toSet()));

        return employeeDTO;
    }

    public List<EmployeeDTO> toEmployeeDTOs(List<UserEntity> users) {
        if (users == null) return null;

        return users.stream()
                .distinct()
                .map(user -> {
                    EmployeeDTO employeeDTO = new EmployeeDTO();
                    employeeDTO.setId(user.getId());
                    employeeDTO.setName(user.getName());
                    employeeDTO.setSkills(user.getUserSkills()
                            .stream()
                            .map(userSkill -> {
                                IdUserSkill idUserSkill = userSkill.getId();
                                return EmployeeSkill.values()[idUserSkill.getSkillId() - 1];
                            })
                            .collect(Collectors.toSet()));
                    employeeDTO.setDaysAvailable(user.getUserOperationTimes()
                            .stream()
                            .map(userSkill -> {
                                IdUserOperationTime idUserSkill = userSkill.getIdUserOperationTime();
                                return DayOfWeek.of(idUserSkill.getOperationTimeId());
                            })
                            .collect(Collectors.toSet()));
                    return employeeDTO;
                })
                .collect(Collectors.toList());
    }
}
