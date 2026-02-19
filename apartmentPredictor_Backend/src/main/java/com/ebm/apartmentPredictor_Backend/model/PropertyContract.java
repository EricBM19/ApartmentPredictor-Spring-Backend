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
    private Long valueRealState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    @JsonIgnore
    private Apartment apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Owner owner;

    public PropertyContract() {}

    public PropertyContract(LocalDate contractDate, String registerNumberPropiertyContract, Long valueRealState, Apartment apartment, Owner owner) {
        this.contractDate = contractDate;
        this.registerNumberPropertyContract = registerNumberPropiertyContract;
        this.valueRealState = valueRealState;
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

    public Long getValueRealState() {
        return valueRealState;
    }

    public void setValueRealState(Long valueRealState) {
        this.valueRealState = valueRealState;
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
                ", valueRealState=" + valueRealState +
                ", apartment=" + apartment +
                ", owner=" + owner +
                '}';
    }
}
