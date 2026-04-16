package com.ebm.apartmentPredictor_Backend.controller;

import com.ebm.apartmentPredictor_Backend.model.School;
import com.ebm.apartmentPredictor_Backend.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/schools")
public class SchoolRestController {

    @Autowired
    SchoolService schoolService;

    @GetMapping("/{id}")
    public ResponseEntity<School> findById(@PathVariable Long id) {
        School school = schoolService.findSchoolById(id);
        return ResponseEntity.ok(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        return ResponseEntity.ok(schoolService.findAll());
    }

    @PostMapping
    public School createSchool(@RequestBody School school) {
        return schoolService.createSchool(school);
    }

    @DeleteMapping("/{id}")
    public void deleteSchoolById(@PathVariable Long id) {
        schoolService.deleteSchool(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School school) {
        School schoolUpdated = schoolService.updateSchoolById(id, school);
        return ResponseEntity.ok(schoolUpdated);
    }
}