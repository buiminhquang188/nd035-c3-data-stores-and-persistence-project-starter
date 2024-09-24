package com.udacity.jdnd.course3.critter.pet.controller.impl;

import com.udacity.jdnd.course3.critter.pet.controller.PetController;
import com.udacity.jdnd.course3.critter.pet.dto.PetDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetControllerImpl implements PetController {
    @Override
    public PetDTO savePet(PetDTO petDTO) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PetDTO getPet(long petId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<PetDTO> getPets() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<PetDTO> getPetsByOwner(long ownerId) {
        throw new UnsupportedOperationException();
    }
}
