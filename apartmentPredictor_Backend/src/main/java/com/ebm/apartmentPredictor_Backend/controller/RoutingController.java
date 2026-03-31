package com.ebm.apartmentPredictor_Backend.controller;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.School;
import com.ebm.apartmentPredictor_Backend.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/routing")
public class RoutingController {

    @Autowired
    RoutingService routingService;

    @GetMapping("/walking")
    public Map<String, Object> getWalkingInfo(
            @RequestParam double lat1,
            @RequestParam double lon1,
            @RequestParam double lat2,
            @RequestParam double lon2
    ) {
        Apartment apartment = new Apartment();
        apartment.setLatitude(lat1);
        apartment.setLongitude(lon1);

        School school = new School();
        school.setLatitude(lat2);
        school.setLongitude(lon2);

        double distance = routingService.getWalkingDistance(apartment,school);
        double time = routingService.getWalkingTimeInMinutes(apartment,school);

        Map<String, Object> response = new HashMap<>();
        response.put("distanceMeters", distance);
        response.put("timeMinutes", time);

        return response;
    }
}
