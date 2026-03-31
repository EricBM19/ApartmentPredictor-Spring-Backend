package com.ebm.apartmentPredictor_Backend.service;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.School;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutingService {

    @Autowired
    GraphHopper graphHopper;

    public double getWalkingDistance(Apartment apartment, School school) {
        GHRequest request = new GHRequest(apartment.getLatitude(),apartment.getLongitude(),
                school.getLatitude(), school.getLongitude()).setProfile("foot");

        GHResponse response = graphHopper.route(request);
        if (response.hasErrors()) {
            throw new RuntimeException(response.getErrors().toString());
        }

        return response.getBest().getDistance();
    }

    public double getWalkingTimeInMinutes(Apartment apartment, School school) {
        GHRequest request = new GHRequest(apartment.getLatitude(),apartment.getLongitude(),
                school.getLatitude(), school.getLongitude()).setProfile("foot");

        GHResponse response = graphHopper.route(request);
        if (response.hasErrors()) {
            throw new RuntimeException(response.getErrors().toString());
        }

        long timeMs = response.getBest().getTime();

        return (timeMs/1000.0)/60.0;
    }
}
