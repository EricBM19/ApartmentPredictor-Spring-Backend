package com.ebm.apartmentPredictor_Backend.utils;

import com.ebm.apartmentPredictor_Backend.model.Owner;
import com.ebm.apartmentPredictor_Backend.repository.OwnerRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OwnerPopulator {

    @Autowired
    OwnerRepository ownerRepository;

    public void populateOwner(int quantity) {

    }

    private List<Owner> generateOwner (int quantity) {
        List<Owner> generatedOwners = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < quantity; i++) {
            Owner owner = new Owner();
            owner.setName(faker.name().firstName());
            owner.setSurname(faker.name().lastName());
            owner.setEmail(faker.internet().emailAddress());
            owner.setAge(faker.number().numberBetween(18,80));
            owner.setActive(faker.bool().bool());
            owner.setBusiness(faker.bool().bool());
            owner.setIdLegalOwner(faker.idNumber().valid());
        }

        return generatedOwners;
    }
}
