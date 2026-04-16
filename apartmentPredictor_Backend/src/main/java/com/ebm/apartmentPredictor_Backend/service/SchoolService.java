package com.ebm.apartmentPredictor_Backend.service;

import com.ebm.apartmentPredictor_Backend.model.School;
import com.ebm.apartmentPredictor_Backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SchoolService {
    @Autowired
    SchoolRepository schoolRepository;

    public School findSchoolById(Long id) {
        Optional<School> schoolOpt = schoolRepository.findById(id);
        if (schoolOpt.isEmpty()) {
            throw new RuntimeException("School not found.");
        }
        return schoolOpt.get();
    }

    public List<School> findAll() {
        List<School> schools = new ArrayList<>();
        schoolRepository.findAll().forEach(schools::add);
        return schools;
    }

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    public School updateSchoolById(Long id, School newSchoolData) {
        Optional<School> schoolOpt = schoolRepository.findById(id);
        if (schoolOpt.isEmpty()) {
            throw new RuntimeException("School not found.");
        }
        School existingSchool = schoolOpt.get();

        existingSchool.setName(newSchoolData.getName());
        existingSchool.setType(newSchoolData.getType());
        existingSchool.setLocation(newSchoolData.getLocation());
        existingSchool.setRating(newSchoolData.getRating());
        existingSchool.setPublicSchool(newSchoolData.isPublicSchool());
        existingSchool.setLatitude(newSchoolData.getLatitude());
        existingSchool.setLongitude(newSchoolData.getLongitude());

        return schoolRepository.save(existingSchool);
    }
}