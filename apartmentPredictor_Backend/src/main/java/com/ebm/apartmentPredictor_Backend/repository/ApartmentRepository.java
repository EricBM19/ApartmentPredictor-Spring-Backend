package com.ebm.apartmentPredictor_Backend.repository;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApartmentRepository extends CrudRepository <Apartment, Long> {

    @Query(value = "SELECT * FROM apartment WHERE price BETWEEN :min AND :max", nativeQuery = true)
    List<Apartment> findByPriceBetween(@Param("min") Long min, @Param("max") Long max);

    @Query(value = "SELECT * FROM apartment WHERE bedrooms = :bedrooms", nativeQuery = true)
    List<Apartment> findByBedrooms(@Param("bedrooms") int bedrooms);

    @Query(value = """
    SELECT a.* 
    FROM apartment a
    JOIN apartment_school_joinTable s 
        ON a.id = s.apartment_id
    WHERE s.school_id = :#{#school.id}
""", nativeQuery = true)
    List<Apartment> findBySchoolsContaining(@Param("school") School school);
}
