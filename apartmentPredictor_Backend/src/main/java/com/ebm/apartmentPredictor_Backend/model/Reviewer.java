package com.ebm.apartmentPredictor_Backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Reviewer extends Person{

    private String reviewerType;
    private int experienceYears;
    private double averageRating;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    public Reviewer() {
        super();
    }

    public Reviewer(String name, String surname, String email, int age, String reviewerType, int experienceYears, double averageRating) {
        super(name, surname, email, age);
        this.reviewerType = reviewerType;
        this.experienceYears = experienceYears;
        this.averageRating = averageRating;
    }

    public String getReviewerType() {
        return reviewerType;
    }

    public void setReviewerType(String reviewerType) {
        this.reviewerType = reviewerType;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setReviewer(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setReviewer(null);
    }

    @Override
    public String toString() {
        return "Reviewer{" +
                super.toString().replace("}", "") +
                "reviewerType='" + reviewerType + '\'' +
                ", experienceYears=" + experienceYears +
                ", averageRating=" + averageRating +
                '}';
    }
}
