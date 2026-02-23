package com.ebm.apartmentPredictor_Backend.PopulatorsTests;

import com.ebm.apartmentPredictor_Backend.model.*;
import com.ebm.apartmentPredictor_Backend.repository.ApartmentRepository;
import com.ebm.apartmentPredictor_Backend.repository.OwnerRepository;
import com.ebm.apartmentPredictor_Backend.repository.ReviewerRepository;
import com.ebm.apartmentPredictor_Backend.utils.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PopulatorsTests {

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    SchoolPopulator schoolPopulator;

    @Autowired
    ReviewerPopulator reviewerPopulator;

    @Autowired
    OwnerPopulator ownerPopulator;

    @Autowired
    PlainApartmentPopulator plainApartmentPopulator;

    @Autowired
    ApartmentSchoolRelationPopulator apartmentSchoolRelationPopulator;

    @Autowired
    ReviewPopulator reviewPopulator;

    @Autowired
    ReviewerRepository reviewerRepository;

    @Test
    void populateAndAssignAll() {
        int qty = 20;
        List<School> schools = schoolPopulator.populateSchool(qty);
        List<Reviewer> reviewers = reviewerPopulator.populateReviewer(qty);
        List<Owner> owners = ownerPopulator.populateOwner(qty);
        List<Apartment> plainApartments = plainApartmentPopulator.populatePlainApartments(qty);
        apartmentSchoolRelationPopulator.assignSchoolsToApartments(plainApartments,schools);
        List<Review> reviews = reviewPopulator.populateReviews(qty, reviewers, plainApartments);
    }

    @Test
    void testSchoolPopulator() {
        schoolPopulator.populateSchool(10);
    }

    @Test
    void testReviewerPopulator() {
        reviewerPopulator.populateReviewer(10);
    }

    @Test
    void testOwnerPopulator() {
        ownerPopulator.populateOwner(10);
    }

    @Test
    void testPlainApartmentPopulator() {
        plainApartmentPopulator.populatePlainApartments(10);
    }

    @Test
    void testReviewPopulator() {
        List<Reviewer> reviewers = (List<Reviewer>) reviewerRepository.findAll();
        List<Apartment> apartments = (List<Apartment>) apartmentRepository.findAll();
        reviewPopulator.populateReviews(10, reviewers, apartments);
    }
}
