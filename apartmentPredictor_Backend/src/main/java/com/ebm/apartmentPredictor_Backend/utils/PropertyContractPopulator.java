package com.ebm.apartmentPredictor_Backend.utils;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.Owner;
import com.ebm.apartmentPredictor_Backend.model.PropertyContract;
import com.ebm.apartmentPredictor_Backend.repository.PropertyContractRepository;
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
public class PropertyContractPopulator {

    @Autowired
    PropertyContractRepository propertyContractRepository;

    public List<PropertyContract> populatePropertyContracts (int quantity, List<Apartment> apartments, List<Owner> owners) {
        List<PropertyContract> propertyContracts = generatePropertyContracts(quantity, apartments, owners);
        propertyContractRepository.saveAll(propertyContracts);

        return propertyContracts;
    }

    private List<PropertyContract> generatePropertyContracts (int quantity, List<Apartment> apartments, List<Owner> owners) {
        List<PropertyContract> generatedPropertyContracts = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < quantity; i++) {
            PropertyContract propertyContract = new PropertyContract();
            Date date = faker.date().past(3650, TimeUnit.DAYS);
            LocalDate contractDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            propertyContract.setContractDate(contractDate);
            propertyContract.setRegisterNumberPropertyContract(faker.internet().uuid());
            propertyContract.setPropertyValue(faker.number().numberBetween(60000L, 800000L));
            assignApartmentToPropertyContract(faker,apartments,propertyContract);
            assignOwnerToPropertyContract(faker, owners, propertyContract);

            generatedPropertyContracts.add(propertyContract);
        }

        return generatedPropertyContracts;
    }

    private void assignApartmentToPropertyContract (Faker faker, List<Apartment> apartments, PropertyContract propertyContract) {
        Apartment apartment = apartments.get(faker.number().numberBetween(0, apartments.size()));
        apartment.addContracts(propertyContract);
    }

    private void assignOwnerToPropertyContract (Faker faker, List<Owner> owners, PropertyContract propertyContract) {
        Owner owner = owners.get(faker.number().numberBetween(0, owners.size()));
        owner.addContracts(propertyContract);
    }
}
