package com.ebm.apartmentPredictor_Backend.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private int area;
    private int bedrooms;
    private int bathrooms;
    private int stories;
    private String mainroad;
    private String guestroom;
    private String basement;
    private String hotwater;
    private String heating;
    private String airconditioning;
    private int parking;
    private String prefarea;
    private String furnishingStatus;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set <Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set <PropertyContract> propertyContracts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "apartment_school_joinTable",
            joinColumns = @JoinColumn(name = "apartment_id"),
            inverseJoinColumns = @JoinColumn(name = "school_id"))
    private Set<School> schools = new HashSet<>();

    public Apartment() {
    }

    public Apartment(Long price, int area, int bedrooms, int bathrooms, int stories, String mainroad, String guestroom, String basement, String hotwater, String heating, String airconditioning, int parking, String prefarea, String furnishingStatus) {
        this.price = price;
        this.area = area;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.stories = stories;
        this.mainroad = mainroad;
        this.guestroom = guestroom;
        this.basement = basement;
        this.hotwater = hotwater;
        this.heating = heating;
        this.airconditioning = airconditioning;
        this.parking = parking;
        this.prefarea = prefarea;
        this.furnishingStatus = furnishingStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getStories() {
        return stories;
    }

    public void setStories(int stories) {
        this.stories = stories;
    }

    public String getMainroad() {
        return mainroad;
    }

    public void setMainroad(String mainroad) {
        this.mainroad = mainroad;
    }

    public String getGuestroom() {
        return guestroom;
    }

    public void setGuestroom(String guestroom) {
        this.guestroom = guestroom;
    }

    public String getBasement() {
        return basement;
    }

    public void setBasement(String basement) {
        this.basement = basement;
    }

    public String getHotwater() {
        return hotwater;
    }

    public void setHotwater(String hotwater) {
        this.hotwater = hotwater;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public String getAirconditioning() {
        return airconditioning;
    }

    public void setAirconditioning(String airconditioning) {
        this.airconditioning = airconditioning;
    }

    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public String getPrefarea() {
        return prefarea;
    }

    public void setPrefarea(String prefarea) {
        this.prefarea = prefarea;
    }

    public String getFurnishingStatus() {
        return furnishingStatus;
    }

    public void setFurnishingStatus(String furnishingStatus) {
        this.furnishingStatus = furnishingStatus;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setApartment(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setApartment(null);
    }

    public Set<School> getSchools() {
        return schools;
    }

    public void addSchool(School school) {
        schools.add(school);
        school.getApartments().add(this);
    }

    public void removeSchool(School school) {
        schools.remove(school);
        school.getApartments().remove(this);
    }

    public Set<PropertyContract> getPropertyContracts() {
        return propertyContracts;
    }

    public void addContracts(PropertyContract propertyContract) {
        propertyContracts.add(propertyContract);
        propertyContract.setApartment(this);
    }

    public void removePropertyContract(PropertyContract propertyContract) {
        propertyContracts.remove(propertyContract);
        propertyContract.setApartment(null);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", price=" + price +
                ", area=" + area +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", stories=" + stories +
                ", mainroad='" + mainroad + '\'' +
                ", guestroom='" + guestroom + '\'' +
                ", basement='" + basement + '\'' +
                ", hotwater='" + hotwater + '\'' +
                ", heating='" + heating + '\'' +
                ", airconditioning='" + airconditioning + '\'' +
                ", parking=" + parking +
                ", prefarea='" + prefarea + '\'' +
                ", furnishingStatus='" + furnishingStatus + '\'' +
                '}';
    }
}
