# ApartmentPredictor-Spring-Backend

## Product Goal

The current goal of this project is to provide a REST endpoint that allows developers to automatically populate the database with realistic test data, including all related entities and their associations.

The user can specify a numeric parameter representing the desired amount of generated data, enabling fast and consistent setup of the application for development and testing purposes.

## UML

```mermaid
classDiagram
direction TB
    class Person {
        -Long id
        -String name
        -String surname
        -String email
        -String age
    }

    class Reviewer {
        -String reviewerType
        -int experienceYears
        -double averageRating
        -Set <Review> reviews
        -void addReview(Review review)
        -void removeReview(Review review)
    }

    class Owner {
        -boolean isActive
        -boolean isBusiness
        -String idLegalOwner
        -LocalDate registrationDate
        -Set <PropertyContract> propertyContracts
        -void addPropertyContract(PropertyContract propertyContract)
        -void removePropertyContract(PropertyContract propertyContract)
    }

    class Review {
        -Long id
        -String reviewText
        -int rating
        -LocalDate reviewDate
        -String apartmentId
        -Reviewer reviewer
        -Apartment apartment
    }

    class Apartment {
        -Long id
        -Long price
        -int area
        -int bedrooms
        -int bathrooms
        -int stories
        -String mainroad
        -String guestroom
        -String basement
        -String hotwater
        -String heating
        -String airconditioning
        -int parking
        -String prefarea
        -String furnishingStatus
        -List <Review> reviews
        -Set <School> schools
        -Set <PropertyContract> propertyContracts
        -void addReview(Review review)
        -void removeReview(Review review)
        -void addSchool(School school)
        -void removeSchool(School school)
        -void addPropertyContract(PropertyContract propertyContract)
        -void removePropertyContract(PropertyContract propertyContract)
    }

    class PropertyContract {
        -Long id
        -LocalDate contractDate
        -String registerNumberPropiertyContract
        -Long propertyValue
        -Apartment apartment
        -Owner owner
    }

    class School {
        -Long id
        -String name
        -String type
        -String location
        -int rating
        -boolean isPublic
        -Set <Apartment> apartments
        -void addApartment(Apartment apartment)
        -void removeApartment(Apartment apartment)
    }

    <<abstract>> Person

    Reviewer <|-- Person
    Owner <|-- Person
    Reviewer "1" --> "*" Review : writes
    Apartment "1" --> "*" Review
    Apartment "1" --> "*" PropertyContract
    Owner "1" --> "*" PropertyContract
    Apartment "*" --> "*" School : nearby
```

## ApartmentPredictor-Spring-Backend Entities

### Apartment (1.2)

```java
@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private int area;
    private int bedrooms;
    private int bathrooms;
    private int stories;
    private String mainroad;
    private String guestroom;
    private String basement;
    private String hotwater;
    private String heating;
    private String airconditioning;
    private int parking;
    private String prefarea;
    private String furnishingStatus;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set <Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private Set <PropertyContract> propertyContracts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "apartment_school_joinTable",
            joinColumns = @JoinColumn(name = "aparment_id"),
            inverseJoinColumns = @JoinColumn(name = "school_id"))
    private Set<School> schools = new HashSet<>();
}
```

### Person (1.0)

```java
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private int age;
}
```

### Owner (1.2)

```java
@Entity
public class Owner extends Person {

    private boolean isActive;
    private boolean isBusiness;
    private String idLegalOwner;
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<PropertyContract> propertyContracts = new HashSet<>();
}
```

### Reviewer (1.2)

```java
@Entity
public class Reviewer extends Person{

    private String reviewerType;
    private int experienceYears;
    private double averageRating;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();
}
```

### Review (1.2)

```java
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reviewText;
    private int rating;
    private LocalDate reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    @JsonIgnore
    private Reviewer reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", nullable = false)
    @JsonIgnore
    private Apartment apartment;
```

### School (1.1)

```java
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String location;
    private int rating;
    private boolean publicSchool;

    @ManyToMany (mappedBy = "schools")
    private Set<Apartment> apartments = new HashSet<>();
}
```

### PropertyContract (1.2)

```java
@Entity
public class PropertyContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate contractDate;
    private String registerNumberPropertyContract;
    private Long propertyValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    @JsonIgnore
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Owner owner;
}
```

## ApartmentPredictor-Spring-Backend RestController

### PopulateDBController

```java
@RestController
@RequestMapping("/api/v1/")
public class PopulateDBController {

    @Autowired
    PopulateDB populateDB;

    @PostMapping("/populate")
    public String populateDB (@RequestParam int quantity) {
        populateDB.populateAll(quantity);
        return "DataBase populated with " + quantity + " objects per entity.";
    }
}
```

## To Do List (Future implementations)

- Implement service classes for business logic

- Add additional RestControllers per entity

- Implement defensive programming practices

- Explore more JPA inheritance strategies
