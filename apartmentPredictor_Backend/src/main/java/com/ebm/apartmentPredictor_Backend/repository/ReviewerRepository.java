package com.ebm.apartmentPredictor_Backend.repository;

import com.ebm.apartmentPredictor_Backend.model.Reviewer;
import org.springframework.data.repository.CrudRepository;

public interface ReviewerRepository extends CrudRepository <Reviewer, Long> {
}
