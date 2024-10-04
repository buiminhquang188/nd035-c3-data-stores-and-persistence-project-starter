package com.udacity.jdnd.course3.critter.pet.controller.impl;

import com.udacity.jdnd.course3.critter.pet.controller.PetController;
import com.udacity.jdnd.course3.critter.pet.dto.PetDTO;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetControllerImpl implements PetController {
    private final PetService petService;

    public PetControllerImpl(PetService petService) {
        this.petService = petService;
    }

    @Override
    public ResponseEntity<PetDTO> savePet(PetDTO petDTO) {
        PetDTO pet = this.petService.savePet(petDTO);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PetDTO> getPet(Long petId) {
        return ResponseEntity.ok(this.petService.getPet(petId));
    }

    @Override
    public ResponseEntity<List<PetDTO>> getPets() {
        return ResponseEntity.ok(this.petService.getPets());
    }

    @Override
    public ResponseEntity<List<PetDTO>> getPetsByOwner(Long ownerId) {
        return ResponseEntity.ok(this.petService.getPetsByOwner(ownerId));
    }
}
