package com.ebm.apartmentPredictor_Backend;

import com.ebm.apartmentPredictor_Backend.model.*;
import com.ebm.apartmentPredictor_Backend.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class ApartmentPredictorBackendApplicationTests {

	@Autowired
    SchoolRepository schoolRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    ReviewerRepository reviewerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    PropertyContractRepository propertyContractRepository;

    @Test
    void saveOneObjectPerClassTest() {

        School school = new School("Salesians", "Religious", "Badalona", 4, false);
        schoolRepository.save(school);

        Apartment apartment = new Apartment(100000L,70,2,2,0,"yes","yes","no","yes","yes","yes", 1, "yes", "furnished");
        apartmentRepository.save(apartment);

        Owner owner = new Owner("John", "Doe", "jdoe@email.com", 37, true, false, "own1234", LocalDate.now(), 1);
        ownerRepository.save(owner);

        Reviewer reviewer = new Reviewer("Jane", "Doe", "janed@email.com", 35, "New", 0, 4.0);
        reviewerRepository.save(reviewer);

        Review review = new Review("7/10 too much water", 4, LocalDate.now(), reviewer);
        review.setApartment(apartment);
        reviewRepository.save(review);

        PropertyContract propertyContract = new PropertyContract(LocalDate.now(), "PC1234", 123456L);
        propertyContractRepository.save(propertyContract);

    }

}
