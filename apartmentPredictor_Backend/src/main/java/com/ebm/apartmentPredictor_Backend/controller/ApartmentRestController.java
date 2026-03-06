package com.ebm.apartmentPredictor_Backend.controller;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/apartments")
public class ApartmentRestController {

    @Autowired
    ApartmentService apartmentService;

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
}
