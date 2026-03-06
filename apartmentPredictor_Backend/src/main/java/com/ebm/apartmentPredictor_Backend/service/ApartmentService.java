package com.ebm.apartmentPredictor_Backend.service;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {
    @Autowired
    ApartmentRepository apartmentRepository;

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
}
