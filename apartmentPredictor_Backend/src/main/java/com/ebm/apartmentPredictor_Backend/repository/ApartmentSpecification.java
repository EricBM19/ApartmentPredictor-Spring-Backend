package com.ebm.apartmentPredictor_Backend.repository;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.School;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class ApartmentSpecification {

    public static Specification<Apartment> filterBy (
            Long maxPrice,
            Integer minArea,
            Integer minBedrooms,
            Integer minBathrooms,
            Integer minParking,
            String furnishingStatus,
            Boolean mainroad,
            Boolean guestroom,
            Boolean basement,
            Boolean hotwater,
            Boolean heating,
            Boolean airConditioning,
            Boolean prefarea,
            Integer minSchools
    ) {
        return (Root<Apartment> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate p = cb.conjunction();

            if (maxPrice != null && maxPrice > 0) {
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if (minArea != null && minArea > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("area"), minArea));
            }

            if (minBedrooms != null && minBedrooms > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("bedrooms"), minBedrooms));
            }

            if (minBathrooms != null && minBathrooms > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("bathrooms"), minBathrooms));
            }

            if (minParking != null && minParking > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("parking"), minParking));
            }

            if (isNotBlank(furnishingStatus)) {
                p = cb.and(p, cb.like(cb.lower(root.get("furnishingStatus")), furnishingStatus.trim().toLowerCase()));
            }

            p = addYesNoFilter(p, cb, root, "mainroad", mainroad);
            p = addYesNoFilter(p, cb, root, "guestroom", guestroom);
            p = addYesNoFilter(p, cb, root, "basement", basement);
            p = addYesNoFilter(p, cb, root, "hotwater", hotwater);
            p = addYesNoFilter(p, cb, root, "heating", heating);
            p = addYesNoFilter(p, cb, root, "airconditioning", airConditioning);
            p = addYesNoFilter(p, cb, root, "prefarea", prefarea);

            if (minSchools != null && minSchools > 0) {
                Join<Apartment, School> schoolJoin = root.join("schools", JoinType.LEFT);
                query.groupBy(root.get("id"));

                query.having(cb.ge(cb.count(schoolJoin), minSchools));
            }

            return p;
        };
    }

    private static boolean isNotBlank(String s) {
        return s != null &&  !s.trim().isEmpty();
    }

    public static Predicate addYesNoFilter(
            Predicate current,
            CriteriaBuilder cb,
            Root<Apartment> root,
            String fieldName,
            Boolean value) {

        if (value != null) {
            String expected = value ? "yes" : "no";
            return cb.and(current, cb.equal(cb.lower(root.get(fieldName)), expected));
        }
        return current;
    }
}
