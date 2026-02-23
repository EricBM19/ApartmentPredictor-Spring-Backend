package com.ebm.apartmentPredictor_Backend.utils;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.Review;
import com.ebm.apartmentPredictor_Backend.model.Reviewer;
import com.ebm.apartmentPredictor_Backend.repository.ReviewRepository;
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
public class ReviewPopulator {

    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> populateReviews (int quantity, List<Reviewer> reviewers, List<Apartment> apartments) {
        List<Review> reviews = generateReviews(quantity, reviewers, apartments);
        reviewRepository.saveAll(reviews);

        return reviews;
    }

    private List<Review> generateReviews (int quantity, List<Reviewer> reviewers, List<Apartment> apartments) {
        Faker faker = new Faker();
        List<Review> generatedReviews = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Review review = new Review();
            review.setReviewText(faker.lorem().characters(50,200));
            review.setRating(faker.number().numberBetween(1,6));
            Date date = faker.date().past(3650, TimeUnit.DAYS);
            LocalDate reviewDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            review.setReviewDate(reviewDate);
            assignReviewer(faker,reviewers, review);
            assignApartment(faker, apartments, review);

            generatedReviews.add(review);
        }

        return generatedReviews;
    }

    private void assignReviewer(Faker faker, List<Reviewer> reviewers, Review review) {
        Reviewer reviewer = reviewers.get(faker.number().numberBetween(0, reviewers.size()));
        reviewer.addReview(review);

    }

    private void assignApartment (Faker faker, List<Apartment> apartments, Review review) {
        Apartment apartment = apartments.get(faker.number().numberBetween(0, apartments.size()));
        apartment.addReview(review);
    }
}
