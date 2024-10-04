package com.udacity.jdnd.course3.critter.pet.service;

import com.udacity.jdnd.course3.critter.pet.dto.PetDTO;

import java.util.List;

public interface PetService {
    List<PetDTO> getPets();

    PetDTO getPet(Long petId);

    PetDTO savePet(PetDTO petDTO);

    List<PetDTO> getPetsByOwner(Long ownerId);
}
