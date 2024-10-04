package com.udacity.jdnd.course3.critter.pet.controller;

import com.udacity.jdnd.course3.critter.pet.dto.PetDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RequestMapping("/pet")
public interface PetController {
    @PostMapping
    ResponseEntity<PetDTO> savePet(@RequestBody PetDTO petDTO);

    @GetMapping("/{petId}")
    ResponseEntity<PetDTO> getPet(@PathVariable Long petId);

    @GetMapping
    ResponseEntity<List<PetDTO>> getPets();

    @GetMapping("/owner/{ownerId}")
    ResponseEntity<List<PetDTO>> getPetsByOwner(@PathVariable Long ownerId);
}
