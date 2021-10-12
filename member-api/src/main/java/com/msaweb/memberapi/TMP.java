package com.msaweb.memberapi;


import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.springframework.*;

import javax.persistence.*;
import java.util.*;

@Entity
class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    @OneToMany(mappedBy = "person")
    Set<Pet> pets = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public Set<Pet> getPets() {
        //FIXME
        return pets;
    }


}

@Entity
class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person person;

    public String getOwnerName() {
        //FIXME
        return person.getFirstName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPerson(Person person){
        this.person = person;
    }
}

class PersonNotFoundException extends RuntimeException {

}

@Service
class PersonService {

    @PersistenceContext
    private final EntityManager entityManager;


    PersonService(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Transactional
    public void addPet(Long personId, String petName) {

        System.out.println();
        Person person = entityManager.find(Person.class, personId);
        if(person == null){
            throw new PersonNotFoundException();
        }

        Pet pet = new Pet();
        pet.setName(petName);

        Set<Pet> pets = person.getPets();
        pets.add(pet);
        pet.setPerson(person);
        entityManager.persist(person);
        entityManager.persist(pet);

    }
}



