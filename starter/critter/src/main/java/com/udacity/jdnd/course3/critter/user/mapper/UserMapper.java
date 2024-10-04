package com.udacity.jdnd.course3.critter.user.mapper;

import com.udacity.jdnd.course3.critter.pet.entity.PetEntity;
import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.entity.SkillEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserSkillEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
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
                .map(UserSkillEntity::getSkill)
                .map(SkillEntity::getName)
                .collect(Collectors.toSet()));

        return employeeDTO;
    }
}
