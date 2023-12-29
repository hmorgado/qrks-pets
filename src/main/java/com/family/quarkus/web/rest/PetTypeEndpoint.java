package com.family.quarkus.web.rest;

import com.family.quarkus.data.entity.PetType;
import com.family.quarkus.service.PetTypeService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.ResponseHeader;

import java.util.List;

@Path("/rest/petTypes")
public class PetTypeEndpoint {

    private final PetTypeService petTypeService;

    public PetTypeEndpoint(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @ResponseHeader(name = "Access-Control-Allow-Origin", value = "http://localhost:3000")
    @ResponseHeader(name = "Access-Control-Allow-Methods", value = "GET")
    @ResponseHeader(name = "Access-Control-Allow-Headers", value = "Content-Type")
    @GET
    public List<PetType> getPetTypes(){
        System.out.println("called getPetTypes()");
        return this.petTypeService.getAllPetTypes();
    }
}
