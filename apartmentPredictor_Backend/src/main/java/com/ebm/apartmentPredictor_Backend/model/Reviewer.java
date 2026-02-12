package com.ebm.apartmentPredictor_Backend.model;

import jakarta.persistence.Entity;

@Entity
public class Reviewer extends Person{

    private String reviewerType;
    private int experienceYears;
    private double averageRating;

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
