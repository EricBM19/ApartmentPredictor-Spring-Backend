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
        if (quantity <= 0) throw new IllegalArgumentException ("Quantity must be greater than zero");
        List<Review> reviews = generateReviews(quantity, reviewers, apartments);
        if (reviews.isEmpty()) throw new IllegalStateException("No Review items were created.");
        reviewRepository.saveAll(reviews);

        return reviews;
    }

    private List<Review> generateReviews (int quantity, List<Reviewer> reviewers, List<Apartment> apartments) {
        Faker faker = new Faker();
        List<Review> generatedReviews = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Review review = new Review();
            review.setTitle(faker.book().title());
            review.setReviewText(faker.lorem().characters(50,200));
            double rating = (faker.number().randomDouble(2, 10, 50) / 10.0);
            rating = Math.round(rating * 100.0) / 100.0;
            review.setRating(rating);
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
        if (reviewers == null || reviewers.isEmpty()) {
            throw new IllegalArgumentException("Reviewers list must not be empty.");
        }
        Reviewer reviewer = reviewers.get(faker.number().numberBetween(0, reviewers.size()));
        if (reviewer == null) {
            throw new IllegalArgumentException("Reviewer must be persisted first.");
        }
        reviewer.addReview(review);
        reviewer.calculateAverageRating();
    }

    private void assignApartment (Faker faker, List<Apartment> apartments, Review review) {
        if (apartments == null || apartments.isEmpty()) {
            throw new IllegalArgumentException("Apartments list must not be empty.");
        }
        Apartment apartment = apartments.get(faker.number().numberBetween(0, apartments.size()));
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment must be persisted first.");
        }
        apartment.addReview(review);
        apartment.calculateAverageRating();
    }
}
