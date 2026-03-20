package com.ebm.apartmentPredictor_Backend.controller;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.repository.ApartmentRepository;
import com.ebm.apartmentPredictor_Backend.repository.ApartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/apartments")
public class ApartmentFilterRestController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("/filter")
    public ResponseEntity<List<Apartment>> filterApartments (
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) Integer minArea,
            @RequestParam(required = false) Integer minBedrooms,
            @RequestParam(required = false) Integer minBathrooms,
            @RequestParam(required = false) Integer minParking,
            @RequestParam(required = false) String furnishingStatus,
            @RequestParam(required = false) Boolean mainroad,
            @RequestParam(required = false) Boolean guestroom,
            @RequestParam(required = false) Boolean basement,
            @RequestParam(required = false) Boolean hotwater,
            @RequestParam(required = false) Boolean heating,
            @RequestParam(required = false) Boolean airConditioning,
            @RequestParam(required = false) Boolean prefarea,
            @RequestParam(required = false) Integer minSchools
    ) {
        Specification<Apartment> spec = ApartmentSpecification.filterBy(
                maxPrice,minArea,minBedrooms,minBathrooms,minParking,furnishingStatus,
                mainroad,guestroom,basement,hotwater, heating,airConditioning,prefarea,minSchools);

        List<Apartment> apartments = apartmentRepository.findAll(spec);

        return ResponseEntity.ok().body(apartments);
    }
}
