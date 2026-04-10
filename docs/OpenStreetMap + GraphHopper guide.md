# OpenStreetMap + GraphHopper guide

## Product goal

> The main goal of this document is to describe the process of integrating and using OpenStreetMap **offline** (with .sm.pbf maps) and GraphHopper in our ApartmentPredictor Spring Boot project, allowing us to calculate distances between an Apartment and a School using street-level data.

## Steps

First, we need to add the GraphHopper dependency to our **pom.xml**. GraphHopper provides several modules, but we will use **graphhopper-core**, which handles route calculations and routing logic.

```java
<dependency>
    <groupId>com.graphhopper</groupId>
    <artifactId>graphhopper-core</artifactId>
    <version>11.0</version>
    <scope>compile</scope>
</dependency>
```

After adding GraphHopper to our project, then we need to refactor our current entities, **Apartment** and **School**, by adding two new attributes: **latitude** and **longitude**. These attributes are required because GraphHopper uses geographic coordinates to calculate routes and travel distances.

Next, we need to download the map file that we want to work with, in **.osm.pbf** format. This file should be saved in a new package named **/data** at the root of our project.

We can obtain the map file from OpenStreetMap providers, such as [Geofabrik](https://download.geofabrik.de/).

Then, we will create our **GraphHopperConfig** class. This class is responsible for configuring and initializing GraphHopper so that we can calculate routes using OpenStreetMap data.

First, create a new package to store this class, in my case, **/config**,  and then create a new file named **GraphHopperConfig.java**.

```java
@Configuration
public class GraphHopperConfig {

    @Bean
    public GraphHopper graphHopper() {
        GraphHopper hopper = new GraphHopper();

        hopper.setOSMFile("/data/cataluna-260323.osm.pbf");
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
```

- **@Configuration:** Marks this class as a source of Spring Beans definitions. It allows GraphHopper to be instantiated only once and injected into other classes using **@Autowired**.

- **@Bean:** A Spring annotation that registers the returned object (GraphHopper instance) as a Spring-managed bean, so it can be injected wherever needed.

- **GraphHopper graphHopper():** Returns an instance of the GraphHopper routing engine, allowing us to calculate routes for different profiles. It processes the map data and generates the graph of streets and roads.

- **.setOSMFile:** Specifies the path to the map file (**.osm.pbf**) that will be used by GraphHopper for routing calculations.

- **.setGraphHopperLocation():** Specifies the folder where the routing graph will be stored. If the folder does not exist, GraphHopper creates it automatically. This method also allows that next time the app is started, loads the graph quickly instead of reprocess the entire map again (in case we have already created the graph).

- **Custom model:** A GraphHopper configuration that customizes how routes are evaluated, allowing you to influence factors such as priority, speed, and access for different types of roads.

- **.setDistanceInfluence:** Controls how much the distance affects the route calculation. A higher value makes the algorithm prioritize shorter routes, while a lower value gives more importance to speed or other factors.

- **.addToSpeed:** Adjusts the speed used for specific road types or conditions, influencing how fast a segment is considered during route calculation.

- **hopper.setProfiles():** Defines one or more routing profiles, which determine how routes are calculated for different types of movement (e.g., walking, driving, cycling).

- **Statement.If("true", Statement.Op.LIMIT, "5"):** 

- **"foot":** The name of the routing profile for pedestrians.

- **.setWeighting():** Specifies how GraphHopper should calculate the route for the given profile, such as fastest, shortest, or most efficient path depending on the mode of travel.

- **hopper.importOrLoad():** Processes the map file and generates the routing graph if it is the first time running. On subsequent runs, it loads the cached graph, avoiding the need to recreate it.

Once we have created our **GraphHopperConfig** with a profile, we create our **RoutingService**, a Spring Boot service that contains methods to calculate distance and time using our GraphHopper instance.

```java
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
```

We want to work with the GraphHopper instance we created in the previous step, so we inject it into our **@Service** using **@Autowired**, allowing us to use GraphHopper without creating a new instance manually in each service method.

Inside our **RoutingService**, we find the methods used to calculate the time and distance between two locations in our map.

**GHRequest:** It is the class that represents a route request in GraphHopper. It is used to define the route parameters you want to calculate, such as origin, destination, transport profile, etc.

- **getWalkingDistance :** A method responsible for calculating the walking distance between an Apartment and a School. 
  It creates and **GHRequest** using the latitude and longitude of both entities and sets the transport profile, indicating how the route should be calculated.
  The request is sent to GraphHopper using the **route** method.
  Finally, if no errors occur, the method returns the distance of the best route in meters.
- **getWalkingTimeInMinutes:** A method responsible for calculating the walking time between an Apartment and a School.
  It creates a **GHRequest** using the latitude and longitude of both entities and sets the transport profile, which defines how the route should be calculated.
  The request is then sent to GraphHopper using the **route** method.  
  Finally, if no errors occur, the method retrieves the travel time of the best route in milliseconds and converts it into minutes before returning the result.

Both methods return a double.

Once we have the **RoutingService** we inject it, thanks to @Autowired, on our **RoutingController**.

```java
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
```

The **RoutingController** is a REST controller responsible for exposing routing functionality through an HTTP API.  
It provides endpoints to calculate walking distance and time between two geographical points.

- **GET /api/v1/routing/walking:** This endpoint receives two geographic coordinates as request parameters: the origin (lat1, lon1) and the destination (lat2, lon2).
  Then it calls the **RoutingService** to calculate: 
  
  - The walking distance in meters.
  
  - The walking time in minutes.
  
  Finally, it returns both values in a JSON response.

This endpoint has been successfully tested with real data in Postman, as shown in the image: **postman getWalkingInfo**.
