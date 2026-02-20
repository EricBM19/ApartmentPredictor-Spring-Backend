package com.ebm.apartmentPredictor_Backend.utils;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.repository.ApartmentRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlainApartmentPopulator {

    @Autowired
    ApartmentRepository apartmentRepository;

    public List<Apartment> populatePlainApartments (int quantity) {

        List<Apartment> plainApartments = generatePlainApartments(quantity);
        apartmentRepository.saveAll(plainApartments);

        return plainApartments;
    }

    private List <Apartment> generatePlainApartments (int quantity) {
        List<Apartment> generatedPlainApartments = new ArrayList<>();
        Faker faker = new Faker();

        for(int i  = 0; i < quantity; i++)
        {
            Apartment plainApartment = new Apartment();
            plainApartment.setPrice(faker.number().numberBetween(150000L, 1000001L));
            plainApartment.setArea(faker.number().numberBetween(50,201));
            plainApartment.setBedrooms(faker.number().numberBetween(1,4));
            plainApartment.setBathrooms(faker.number().numberBetween(1,3));
            plainApartment.setStories(faker.number().numberBetween(0,3));
            plainApartment.setMainroad(faker.options().option("yes", "no"));
            plainApartment.setGuestroom(faker.options().option("yes", "no"));
            plainApartment.setBasement(faker.options().option("yes", "no"));
            plainApartment.setHotwater(faker.options().option("yes", "no"));
            plainApartment.setHeating(faker.options().option("yes", "no"));
            plainApartment.setAirconditioning(faker.options().option("yes", "no"));
            plainApartment.setParking(faker.number().numberBetween(0,3));
            plainApartment.setPrefarea(faker.options().option("yes", "no"));
            plainApartment.setFurnishingStatus(faker.options().option("furnished", "semi-furnished", "unfurnished"));

            generatedPlainApartments.add(plainApartment);
        }

        return generatedPlainApartments;
    }
}
