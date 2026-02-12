package com.ebm.apartmentPredictor_Backend.repository;

import com.ebm.apartmentPredictor_Backend.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository <Review, Long> {
}
