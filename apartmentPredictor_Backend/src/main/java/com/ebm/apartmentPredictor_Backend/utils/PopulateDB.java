package com.ebm.apartmentPredictor_Backend.utils;

import com.ebm.apartmentPredictor_Backend.model.*;
import com.ebm.apartmentPredictor_Backend.repository.ApartmentRepository;
import com.ebm.apartmentPredictor_Backend.repository.OwnerRepository;
import com.ebm.apartmentPredictor_Backend.repository.ReviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PopulateDB {

    @Autowired
    PlainApartmentPopulator plainApartmentPopulator;

    @Autowired
    SchoolPopulator schoolPopulator;

    @Autowired
    ApartmentSchoolRelationPopulator apartmentSchoolRelationPopulator;

    @Autowired
    ReviewerPopulator reviewerPopulator;

    @Autowired
    ReviewPopulator reviewPopulator;

    @Autowired
    OwnerPopulator ownerPopulator;

    @Autowired
    PropertyContractPopulator propertyContractPopulator;

    public int populateAll(int quantity) {

        List<Apartment> plainApartments = plainApartmentPopulator.populatePlainApartments(quantity);
        List<School> schools = schoolPopulator.populateSchool(quantity);
        List<Apartment> apartmentsWithSchools = apartmentSchoolRelationPopulator.assignSchoolsToApartments(plainApartments, schools);
        List<Reviewer> reviewers = reviewerPopulator.populateReviewer(quantity);
        List<Review> reviews = reviewPopulator.populateReviews(quantity, reviewers, apartmentsWithSchools);
        List<Owner> owners = ownerPopulator.populateOwner(quantity);
        List<PropertyContract> propertyContracts = propertyContractPopulator.populatePropertyContracts(quantity, apartmentsWithSchools, owners);

        return 0;
    }
}
