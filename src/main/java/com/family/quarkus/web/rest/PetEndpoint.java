package com.family.quarkus.web.rest;


import com.family.quarkus.data.entity.Pet;
import com.family.quarkus.data.entity.PetType;
import com.family.quarkus.service.PetService;
import com.family.quarkus.service.PetTypeService;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.ResponseHeader;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;
import java.util.Random;

@Path("/rest/pets")
public class PetEndpoint {
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetEndpoint(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }


    @ResponseHeader(name = "Access-Control-Allow-Origin", value = "http://localhost:3000")
    @ResponseHeader(name = "Access-Control-Allow-Methods", value = "GET")
    @ResponseHeader(name = "Access-Control-Allow-Headers", value = "Content-Type")
    @GET
    public List<Pet> getPets() throws InterruptedException {
        System.out.println("called getPets()");
        return this.petService.getAllPets();
    }

    @OPTIONS
    @ResponseHeader(name = "Access-Control-Allow-Origin", value = "http://localhost:3000")
    @ResponseHeader(name = "Access-Control-Allow-Methods", value = "OPTIONS")
    @ResponseHeader(name = "Access-Control-Allow-Headers", value = "Content-Type")
    public void optionsAdd(){
        print("called optionsAdd()");
    }

    @POST
    @ResponseHeader(name = "Access-Control-Allow-Origin", value = "http://localhost:3000")
    @ResponseHeader(name = "Access-Control-Allow-Methods", value = "OPTIONS, POST")
    @ResponseHeader(name = "Access-Control-Allow-Headers", value = "Content-Type")
    @ResponseStatus(201)
    public Pet addPet(Pet pet){
        print("called addPet()");
        return this.petService.addPet(pet);
    }

    @Path("/{id}")
    @GET
    public Pet getPet(@RestPath("id") long id){
        return this.petService.getPet(id);
    }

    private void print(String s){
        System.out.println(s);
    }

    @Path("/{id}")
    @ResponseHeader(name = "Access-Control-Allow-Origin", value = "http://localhost:3000")
    @ResponseHeader(name = "Access-Control-Allow-Methods", value = "DELETE, OPTIONS")
    @ResponseHeader(name = "Access-Control-Allow-Headers", value = "Content-Type")
    @DELETE
    @ResponseStatus(205)
    public void deletePet(@RestPath("id") long id){
        print("called deletePet()" + id);
        this.petService.deletePet(id);
    }

    @OPTIONS
    @ResponseHeader(name = "Access-Control-Allow-Origin", value = "http://localhost:3000")
    @ResponseHeader(name = "Access-Control-Allow-Methods", value = "DELETE, OPTIONS")
    @ResponseHeader(name = "Access-Control-Allow-Headers", value = "Content-Type")
    @Path("/{id}")
    public void options(){
        print("called options()");
    }

    @Path("/{id}")
    @PUT
    @ResponseStatus(204)
    public void updatePet(@RestPath("id") long id, Pet pet){
        if(id != pet.getId()){
            throw new WebApplicationException(400);
        }
        this.petService.updatePet(pet);
    }

    @Path("/generate")
    @POST
    @ResponseStatus(200)
    public void generatePets(){
        print("called generatePets()");
        String name = "generated";
        Pet pet = new Pet();
        pet.setName(name);

        int randomIndex = new Random().nextInt(1, 4);

        PetType petType = this.petTypeService.getPetType(randomIndex);
        pet.setPetType(petType);

        this.petService.addPet(pet);
    }
}
