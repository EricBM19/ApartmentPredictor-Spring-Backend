package com.ebm.apartmentPredictor_Backend.repository;

import com.ebm.apartmentPredictor_Backend.model.School;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository <School, Long> {
}
