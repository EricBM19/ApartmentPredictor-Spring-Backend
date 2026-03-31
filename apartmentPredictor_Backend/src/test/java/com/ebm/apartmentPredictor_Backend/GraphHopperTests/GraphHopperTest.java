package com.ebm.apartmentPredictor_Backend.GraphHopperTests;

import com.ebm.apartmentPredictor_Backend.model.Apartment;
import com.ebm.apartmentPredictor_Backend.model.School;
import com.ebm.apartmentPredictor_Backend.service.RoutingService;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GraphHopperTest {

    @Autowired
    RoutingService routingService;

    @MockitoBean
    GraphHopper graphHopper;

    @Test
    void getWalkingDistanceTest() {

        GHResponse mockResponse = mock(GHResponse.class);
        ResponsePath mockPath = mock(ResponsePath.class);

        when(graphHopper.route(any())).thenReturn(mockResponse);
        when(mockResponse.hasErrors()).thenReturn(false);
        when(mockResponse.getBest()).thenReturn(mockPath);
        when(mockPath.getDistance()).thenReturn(8400.0);

        Apartment apartment = new Apartment(350000L,60,3,2,1,
                "yes","yes","yes","yes","yes","yes",1,
                "yes","furnished",3.5,41.39412, 2.169779);

        School school = new School("Salesians BDN","religious","Badalona",4,
                false,41.443509,2.240114);

        double distance  = routingService.getWalkingDistance(apartment,school);
        System.out.println("Distancia real caminando: " + distance + " metros");
    }
}
