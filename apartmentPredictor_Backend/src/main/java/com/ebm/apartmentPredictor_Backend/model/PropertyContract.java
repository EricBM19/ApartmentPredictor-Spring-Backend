package com.ebm.apartmentPredictor_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

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

    public PropertyContract() {}

    public PropertyContract(LocalDate contractDate, String registerNumberPropertyContract, Long propertyValue, Apartment apartment, Owner owner) {
        this.contractDate = contractDate;
        this.registerNumberPropertyContract = registerNumberPropertyContract;
        this.propertyValue = propertyValue;
        this.apartment = apartment;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getRegisterNumberPropertyContract() {
        return registerNumberPropertyContract;
    }

    public void setRegisterNumberPropertyContract(String registerNumberPropertyContract) {
        this.registerNumberPropertyContract = registerNumberPropertyContract;
    }

    public Long getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(Long propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "PropertyContract{" +
                "id=" + id +
                ", contractDate=" + contractDate +
                ", registerNumberPropiertyContract='" + registerNumberPropertyContract + '\'' +
                ", valueRealState=" + propertyValue +
                ", apartment=" + apartment +
                ", owner=" + owner +
                '}';
    }
}
