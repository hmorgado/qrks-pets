package com.family.quarkus.service;

import com.family.quarkus.data.entity.PetType;
import com.family.quarkus.data.repository.PetTypeRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PetTypeService {
    private final PetTypeRepository petTypeRepository;

    public PetTypeService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    public PetType getPetType(long id) { return this.petTypeRepository.findById(id); }

    public List<PetType> getAllPetTypes() { return this.petTypeRepository.listAll(); }
}
