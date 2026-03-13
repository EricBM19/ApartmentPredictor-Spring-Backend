package com.ebm.apartmentPredictor_Backend.controller;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.Review;
import com.ebm.apartmentPredictor_Backend.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/apartments")
public class ApartmentRestController {

    @Autowired
    ApartmentService apartmentService;

    //Basic CRUD operations
    @GetMapping("/{id}")
    public ResponseEntity<Apartment> findById(@PathVariable Long id) {
        Apartment apartment = apartmentService.findApartmentById(id);
        return ResponseEntity.ok(apartment);
    }

    @GetMapping
    public ResponseEntity<List<Apartment>> getAllApartments() {
        return ResponseEntity.ok(apartmentService.findAll());
    }

    @PostMapping
    public Apartment createApartment(@RequestBody Apartment apartment){
        return apartmentService.createApartment(apartment);
    }

    @DeleteMapping("/{id}")
    public void deleteApartmentById(@PathVariable Long id){
        apartmentService.deleteApartment(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apartment> updateApartment(@PathVariable Long id, @RequestBody Apartment apartment) {
        Apartment apartmentUpdated = apartmentService.updateApartmentById(id, apartment);
        return ResponseEntity.ok(apartmentUpdated);
    }

    //JPA querys
    @GetMapping("/{id}/reviews")
    public ResponseEntity<Set<Review>> findAllReviews (@PathVariable Long id) {
        return ResponseEntity.ok(apartmentService.findAllReviews(id));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Apartment>> findByPriceBetween(@RequestParam Long minPrice,@RequestParam Long maxPrice) {
        return ResponseEntity.ok(apartmentService.findByPriceBetween(minPrice,maxPrice));
    }

    @GetMapping("/bedrooms")
    public ResponseEntity<List<Apartment>> findByBedrooms (@RequestParam int bedroomsNum) {
        return ResponseEntity.ok(apartmentService.findByBedrooms(bedroomsNum));
    }
}
