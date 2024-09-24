package com.udacity.jdnd.course3.critter.pet.controller;

import com.udacity.jdnd.course3.critter.pet.dto.PetDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RequestMapping("/pet")
public interface PetController {
    @PostMapping
    PetDTO savePet(@RequestBody PetDTO petDTO);

    @GetMapping("/{petId}")
    PetDTO getPet(@PathVariable long petId);

    @GetMapping
    List<PetDTO> getPets();

    @GetMapping("/owner/{ownerId}")
    List<PetDTO> getPetsByOwner(@PathVariable long ownerId);
}
