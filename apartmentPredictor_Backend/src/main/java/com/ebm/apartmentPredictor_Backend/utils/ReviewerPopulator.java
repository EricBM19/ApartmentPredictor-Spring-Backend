package com.ebm.apartmentPredictor_Backend.utils;

import com.ebm.apartmentPredictor_Backend.model.Reviewer;
import com.ebm.apartmentPredictor_Backend.repository.ReviewerRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewerPopulator {

    @Autowired
    ReviewerRepository reviewerRepository;

    public void populateReviewer(int quantity) {
        List<Reviewer> reviewers = generateReviewers(quantity);
        reviewerRepository.saveAll(reviewers);
    }

    private List<Reviewer> generateReviewers (int quantity) {
        List<Reviewer> generatedReviewers = new ArrayList<>();
        Faker faker = new Faker();

        for(int i = 0; i < quantity; i++) {
            Reviewer reviewer = new Reviewer();
            reviewer.setName(faker.name().firstName());
            reviewer.setSurname(faker.name().lastName());
            reviewer.setEmail(faker.internet().emailAddress());
            reviewer.setAge(faker.number().numberBetween(18,71));
            String reviewerType = faker.options().option("CASUAL", "EXPERIENCED", "PROFESSIONAL");
            reviewer.setReviewerType(reviewerType);
            reviewer.setExperienceYears(assignExperienceYear(reviewerType, faker));
            double rating = faker.number().randomDouble(2, 10, 50) / 10.0;
            rating = Math.round(rating * 100.0) / 100.0;
            reviewer.setAverageRating(rating);

            generatedReviewers.add(reviewer);
        }

        return generatedReviewers;
    }

    public int assignExperienceYear(String reviewerType, Faker faker) {
        int year = 0;

        if (reviewerType.equals("CASUAL")) {
            year = faker.number().numberBetween(0,3);
        }
        else if (reviewerType.equals("EXPERIENCED")) {
            year = faker.number().numberBetween(3,6);
        } else if (reviewerType.equals("PROFESSIONAL")) {
            year = faker.number().numberBetween(6,16);
        }
        return year;
    }
}
