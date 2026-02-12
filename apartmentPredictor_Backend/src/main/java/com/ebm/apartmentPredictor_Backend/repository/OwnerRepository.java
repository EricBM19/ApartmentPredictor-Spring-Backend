package com.ebm.apartmentPredictor_Backend.repository;

import com.ebm.apartmentPredictor_Backend.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository <Owner, Long> {
}
