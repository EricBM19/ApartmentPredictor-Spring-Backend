package com.ebm.apartmentPredictor_Backend.service;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.Review;
import com.ebm.apartmentPredictor_Backend.model.School;
import com.ebm.apartmentPredictor_Backend.repository.ApartmentRepository;
import com.ebm.apartmentPredictor_Backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApartmentService {
    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    SchoolRepository schoolRepository;

    public Apartment findApartmentById(Long id) {
        Optional<Apartment> apartmentOpt = apartmentRepository.findById(id);
        if (apartmentOpt.isEmpty()) {
            throw new RuntimeException("Apartment not found.");
        }
        return apartmentOpt.get();
    }

    public List<Apartment> findAll() {
        List<Apartment> apartments = new ArrayList<>();
        apartmentRepository.findAll().forEach(apartments::add);
        return apartments;
    }

    public Apartment createApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }

    public Apartment updateApartmentById(Long id, Apartment newApartmentData) {
        Optional<Apartment> apartmentOpt = apartmentRepository.findById(id);
        if (apartmentOpt.isEmpty()) {
            throw new RuntimeException("Apartment not found.");
        }
        Apartment existingApartment = apartmentOpt.get();

        existingApartment.setPrice(newApartmentData.getPrice());
        existingApartment.setArea(newApartmentData.getArea());
        existingApartment.setBedrooms(newApartmentData.getBedrooms());
        existingApartment.setBathrooms(newApartmentData.getBathrooms());
        existingApartment.setStories(newApartmentData.getStories());
        existingApartment.setMainroad(newApartmentData.getMainroad());
        existingApartment.setGuestroom(newApartmentData.getGuestroom());
        existingApartment.setBasement(newApartmentData.getBasement());
        existingApartment.setHotwater(newApartmentData.getHotwater());
        existingApartment.setHeating(newApartmentData.getHeating());
        existingApartment.setAirconditioning(newApartmentData.getAirconditioning());
        existingApartment.setParking(newApartmentData.getParking());
        existingApartment.setPrefarea(newApartmentData.getPrefarea());
        existingApartment.setFurnishingStatus(newApartmentData.getFurnishingStatus());

        return apartmentRepository.save(existingApartment);
    }

    public List<Apartment> findByPriceBetween (Long minPrice, Long maxPrice) {
        return apartmentRepository.findByPriceBetween(minPrice,maxPrice);
    }

    public Set<Review> findAllReviews (Long id) {
        Optional<Apartment> apartmentOpt = apartmentRepository.findById(id);
        if (apartmentOpt.isEmpty()) {
            throw new RuntimeException("Apartment not found.");
        }
        Set<Review> reviews = apartmentOpt.get().getReviews();

        return reviews;
    }

    public Set<School> findAllSchools (Long id) {
        Optional<Apartment> apartmentOpt = apartmentRepository.findById(id);
        if (apartmentOpt.isEmpty()) {
            throw new RuntimeException("Apartment not found.");
        }
        Set<School> schools = apartmentOpt.get().getSchools();

        return schools;
    }

    public List<Apartment> findByBedrooms(int bedroomsNum) {
        return apartmentRepository.findByBedrooms(bedroomsNum);
    }

    public List<Apartment> findBySchoolId(Long schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found"));

        return apartmentRepository.findBySchoolsContaining(school);
    }
}
