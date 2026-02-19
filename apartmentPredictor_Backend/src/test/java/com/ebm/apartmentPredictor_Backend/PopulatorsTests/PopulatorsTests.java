package com.ebm.apartmentPredictor_Backend.PopulatorsTests;

import com.ebm.apartmentPredictor_Backend.utils.ReviewerPopulator;
import com.ebm.apartmentPredictor_Backend.utils.SchoolPopulator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PopulatorsTests {

    @Autowired
    SchoolPopulator schoolPopulator;

    @Autowired
    ReviewerPopulator reviewerPopulator;

    @Test
    void populateAll() {
        int qty = 10;
        schoolPopulator.populateSchool(qty);
        reviewerPopulator.populateReviewer(qty);
    }

    @Test
    void testSchoolPopulator() {
        schoolPopulator.populateSchool(10);
    }

    @Test
    void testReviewerPopulator() {
        reviewerPopulator.populateReviewer(10);
    }
}
