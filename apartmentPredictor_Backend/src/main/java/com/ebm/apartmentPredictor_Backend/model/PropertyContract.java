package com.ebm.apartmentPredictor_Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class PropertyContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate contractDate;
    private String registerNumberPropiertyContract;
    private Long valueRealState;

    public PropertyContract() {
    }

    public PropertyContract(LocalDate contractDate, String registerNumberPropiertyContract, Long valueRealState) {
        this.contractDate = contractDate;
        this.registerNumberPropiertyContract = registerNumberPropiertyContract;
        this.valueRealState = valueRealState;
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

    public String getRegisterNumberPropiertyContract() {
        return registerNumberPropiertyContract;
    }

    public void setRegisterNumberPropiertyContract(String registerNumberPropiertyContract) {
        this.registerNumberPropiertyContract = registerNumberPropiertyContract;
    }

    public Long getValueRealState() {
        return valueRealState;
    }

    public void setValueRealState(Long valueRealState) {
        this.valueRealState = valueRealState;
    }

    @Override
    public String toString() {
        return "PropertyContract{" +
                "id=" + id +
                ", contractDate=" + contractDate +
                ", registerNumberPropiertyContract='" + registerNumberPropiertyContract + '\'' +
                ", valueRealState=" + valueRealState +
                '}';
    }
}
