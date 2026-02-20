package com.ebm.apartmentPredictor_Backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String location;
    private int rating;
    private boolean publicSchool;

    @ManyToMany (mappedBy = "schools", fetch = FetchType.EAGER)
    private Set<Apartment> apartments = new HashSet<>();

    public School() {
    }

    public School(String name, String type, String location, int rating, boolean publicSchool) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.rating = rating;
        this.publicSchool = publicSchool;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isPublicSchool() {
        return publicSchool;
    }

    public void setPublicSchool(boolean publicSchool) {
        this.publicSchool = publicSchool;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
        apartment.getSchools().add(this);
    }

    public void removeApartment(Apartment apartment) {
        apartments.remove(apartment);
        apartment.getSchools().remove(this);
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", publicSchool=" + publicSchool +
                '}';
    }
}
