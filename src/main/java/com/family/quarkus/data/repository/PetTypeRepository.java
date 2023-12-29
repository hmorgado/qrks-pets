package com.family.quarkus.data.repository;

import com.family.quarkus.data.entity.PetType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PetTypeRepository implements PanacheRepository<PetType> {

}
