package com.family.quarkus.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name="pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pet_id")
    private long pet_id;
    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="type_id")
    private PetType type;


    public long getId() {
        return pet_id;
    }

    public void setId(long id) {
        this.pet_id = id;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "pet_id=" + pet_id +
                ", name='" + name + '\'' +
                ", petType=" + type.getName() +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return this.type;
    }

    public void setPetType(PetType type) {
        this.type = type;
    }
}
