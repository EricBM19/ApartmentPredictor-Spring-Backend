package com.ebm.apartmentPredictor_Backend.config;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.Profile;
import com.graphhopper.json.Statement;
import com.graphhopper.util.CustomModel;
import org.hibernate.query.spi.Limit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphHopperConfig {

    @Bean
    public GraphHopper graphHopper() {
        GraphHopper hopper = new GraphHopper();

        hopper.setOSMFile("data/cataluna-260323.osm.pbf");
        hopper.setGraphHopperLocation("graph-cache");

        CustomModel customModel = new CustomModel();
        customModel.setDistanceInfluence(1000.0);

        customModel.addToSpeed(Statement.If("true", Statement.Op.LIMIT, "5"));

        hopper.setProfiles(
                new Profile("foot")
                        .setWeighting("custom")
                        .setCustomModel(customModel)
        );

        hopper.importOrLoad();

        return hopper;
    }
}
