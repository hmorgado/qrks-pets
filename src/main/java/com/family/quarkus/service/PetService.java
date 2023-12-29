package com.family.quarkus.service;

import com.family.quarkus.data.entity.Pet;
import com.family.quarkus.data.repository.PetRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets(){
        return this.petRepository.listAll();
    }

    @Transactional
    public Pet addPet(Pet pet){
        this.petRepository.persist(pet);
        return pet;
    }

    public Pet getPet(long id){
        return this.petRepository.findById(id);
    }

    @Transactional
    public void deletePet(long id){
        this.petRepository.deleteById(id);
    }

    @Transactional
    public Pet updatePet(Pet pet){
        Pet entity = this.petRepository.findById(pet.getId());
        if(entity == null){
            throw new NotFoundException();
        }
        entity.setName(pet.getName());
        this.petRepository.persist(entity);
        return entity;
    }
}
