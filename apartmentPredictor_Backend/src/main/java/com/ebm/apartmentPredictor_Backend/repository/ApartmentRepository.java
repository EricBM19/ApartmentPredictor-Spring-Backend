package com.ebm.apartmentPredictor_Backend.repository;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApartmentRepository extends CrudRepository <Apartment, Long> {

    List<Apartment> findByPriceBetween(Long minPrice, Long maxPrice);

    List<Apartment> findByBedrooms (int bedroomsNum);
}
