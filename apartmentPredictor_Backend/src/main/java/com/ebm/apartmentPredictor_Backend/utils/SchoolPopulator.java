package com.ebm.apartmentPredictor_Backend.utils;

import com.ebm.apartmentPredictor_Backend.model.School;
import com.ebm.apartmentPredictor_Backend.repository.SchoolRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SchoolPopulator {

    @Autowired
    SchoolRepository schoolRepository;

    public void populateSchool (int quantity) {
        List<School> schools = generateSchools(quantity);
        schoolRepository.saveAll(schools);
    }

    private List<School> generateSchools (int quantity) {
        List<School> generatedSchools = new ArrayList<>();
        Set<String> usedNames = new HashSet<>();
        Faker faker = new Faker();

        //For that creates Schools with fake atributes based on the quantity we want to generate
        String name;
        for (int i = 0; i < quantity; i++) {
            do {
                name = faker.educator().secondarySchool();
            }while (!usedNames.add(name));

            School school = new School();
            school.setName(name);
            school.setType(faker.options().option("religious", "non-religious"));
            school.setLocation(faker.address().streetAddress());
            school.setRating(faker.number().numberBetween(1,6));
            school.setPublicSchool(faker.bool().bool());

            generatedSchools.add(school);
        }

        return generatedSchools;
    }
}
