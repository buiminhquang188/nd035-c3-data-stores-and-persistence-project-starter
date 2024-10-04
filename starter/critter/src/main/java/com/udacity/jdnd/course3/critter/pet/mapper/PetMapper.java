package com.udacity.jdnd.course3.critter.pet.mapper;

import com.udacity.jdnd.course3.critter.common.mapper.BaseMapper;
import com.udacity.jdnd.course3.critter.pet.dto.PetDTO;
import com.udacity.jdnd.course3.critter.pet.entity.PetEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetMapper implements BaseMapper<PetEntity, PetDTO> {
    @Override
    public List<PetDTO> entitiesToDTOs(List<PetEntity> petsEntity) {
        return petsEntity.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO entityToDTO(PetEntity petEntity) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(petEntity.getId());
        petDTO.setType(petEntity.getType()
                .getType());
        petDTO.setName(petEntity.getName());
        petDTO.setOwnerId(petEntity.getUser()
                .getId());
        petDTO.setBirthDate(petEntity.getBirthDate());
        petDTO.setNotes(petEntity.getNotes());
        return petDTO;
    }
}
