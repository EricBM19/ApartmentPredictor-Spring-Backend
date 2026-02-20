package com.ebm.apartmentPredictor_Backend.utils;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.School;
import com.ebm.apartmentPredictor_Backend.repository.ApartmentRepository;
import com.ebm.apartmentPredictor_Backend.repository.SchoolRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class ApartmentSchoolRelationPopulator {

    @Autowired
    ApartmentRepository apartmentRepository;

    public void assignSchoolsToApartments(List<Apartment> apartments, List<School> schools) {
        Faker faker = new Faker();
        Random random = new Random();

        for (Apartment apartment : apartments) {
            int maxNumOfSchoolsPerApartment = faker.number().numberBetween(0, Math.min(5, schools.size() + 1));

            while (apartment.getSchools().size() != maxNumOfSchoolsPerApartment) {
                apartment.addSchool(schools.get(random.nextInt(schools.size())));
            }
            apartmentRepository.save(apartment);
        }
    }
}
