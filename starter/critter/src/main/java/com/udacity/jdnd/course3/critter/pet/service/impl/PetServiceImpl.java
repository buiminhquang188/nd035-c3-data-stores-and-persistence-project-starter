package com.udacity.jdnd.course3.critter.pet.service.impl;

import com.udacity.jdnd.course3.critter.common.exception.runtime.NotFoundException;
import com.udacity.jdnd.course3.critter.pet.dto.PetDTO;
import com.udacity.jdnd.course3.critter.pet.entity.PetEntity;
import com.udacity.jdnd.course3.critter.pet.entity.PetTypeEntity;
import com.udacity.jdnd.course3.critter.pet.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.pet.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.repository.PetTypeRepository;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;
import com.udacity.jdnd.course3.critter.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;
    private final UserRepository userRepository;
    private final PetMapper petMapper;

    public PetServiceImpl(PetRepository petRepository, PetTypeRepository petTypeRepository, UserRepository userRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
        this.userRepository = userRepository;
        this.petMapper = petMapper;
    }

    @Override
    public List<PetDTO> getPets() {
        List<PetEntity> pets = this.petRepository.findAll();
        return this.petMapper.entitiesToDTOs(pets);
    }

    @Override
    public PetDTO getPet(Long petId) {
        Optional<PetEntity> pet = this.petRepository.findById(petId);
        return this.petMapper.entityToDTO(
                pet.orElseThrow(() -> new NotFoundException("Pet not found"))
        );
    }

    @Transactional
    @Override
    public PetDTO savePet(PetDTO petDTO) {
        PetEntity pet = new PetEntity();

        Optional<PetTypeEntity> petTypeEntity = this.petTypeRepository.findById(petDTO.getType()
                .getValue());
        pet.setType(petTypeEntity.orElseThrow(() -> new NotFoundException("Pet type not found")));

        Optional<UserEntity> userEntity = this.userRepository.findById(petDTO.getOwnerId());
        UserEntity user = userEntity.orElseThrow(() -> new NotFoundException("Owner not found"));
        user.addPet(pet);

        pet.setUser(user);
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());

        PetEntity savedPet = this.petRepository.save(pet);

        return this.petMapper.entityToDTO(savedPet);
    }

    @Override
    public List<PetDTO> getPetsByOwner(Long ownerId) {
        List<PetEntity> pet = this.petRepository.findByUserId(ownerId);
        return this.petMapper.entitiesToDTOs(pet);
    }
}
