package com.ebm.apartmentPredictor_Backend.repository;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.School;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApartmentRepository extends CrudRepository <Apartment, Long>, JpaSpecificationExecutor<Apartment> {

    List<Apartment> findByPriceBetween(Long minPrice, Long maxPrice);

    List<Apartment> findByBedrooms (int bedroomsNum);

    List<Apartment> findBySchoolsContaining(School school);
}
