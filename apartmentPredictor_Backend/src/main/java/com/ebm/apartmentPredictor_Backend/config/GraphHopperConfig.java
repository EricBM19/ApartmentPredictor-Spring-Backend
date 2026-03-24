package com.ebm.apartmentPredictor_Backend.config;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.Profile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphHopperConfig {

    @Bean
    public GraphHopper graphHopper() {
        GraphHopper hopper = new GraphHopper();

        hopper.setOSMFile("/data/cataluna-260323.osm.pbf");
        hopper.setGraphHopperLocation("graph-cache");
        hopper.setProfiles(new Profile("foot").setWeighting("fastest"));

        hopper.importOrLoad();

        return hopper;
    }
}
