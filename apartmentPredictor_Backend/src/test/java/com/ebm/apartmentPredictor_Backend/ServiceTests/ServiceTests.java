package com.ebm.apartmentPredictor_Backend.ServiceTests;

import com.ebm.apartmentPredictor_Backend.service.ApartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTests {

    @Autowired
    ApartmentService apartmentService;

    @Test
    void testFindAllReviews ()
    {
        apartmentService.findAllReviews(5L);
    }

    @Test
    void testFindAllSchools ()
    {
        apartmentService.findAllSchools(5L);
    }

    @Test
    void findBySchoolId() {
        apartmentService.findBySchoolId(1L);
    }
}
