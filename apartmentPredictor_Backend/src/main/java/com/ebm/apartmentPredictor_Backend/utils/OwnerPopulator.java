package com.ebm.apartmentPredictor_Backend.utils;

import com.ebm.apartmentPredictor_Backend.model.Owner;
import com.ebm.apartmentPredictor_Backend.repository.OwnerRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class OwnerPopulator {

    @Autowired
    OwnerRepository ownerRepository;

    public List<Owner> populateOwner(int quantity) {
        List<Owner> owners = generateOwner(quantity);
        ownerRepository.saveAll(owners);

        return owners;
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
            Date date = faker.date().past(3650, TimeUnit.DAYS);
            LocalDate registrationDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            owner.setRegistrationDate(registrationDate);

            generatedOwners.add(owner);
        }

        return generatedOwners;
    }
}
