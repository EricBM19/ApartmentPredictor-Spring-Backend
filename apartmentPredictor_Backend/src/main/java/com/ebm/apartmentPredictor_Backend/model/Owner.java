package com.ebm.apartmentPredictor_Backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Owner extends Person {

    private boolean isActive;
    private boolean isBusiness;
    private String idLegalOwner;
    private LocalDate registrationDate;
    private int qtyDaysAsOwner;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PropertyContract> propertyContracts = new HashSet<>();

    public Owner() {
        super();
    }

    public Owner(String name, String surname, String email, int age, boolean isActive, boolean isBusiness, String idLegalOwner, LocalDate registrationDate, int qtyDaysAsOwner) {
        super(name, surname, email, age);
        this.isActive = isActive;
        this.isBusiness = isBusiness;
        this.idLegalOwner = idLegalOwner;
        this.registrationDate = registrationDate;
        this.qtyDaysAsOwner = qtyDaysAsOwner;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    public String getIdLegalOwner() {
        return idLegalOwner;
    }

    public void setIdLegalOwner(String idLegalOwner) {
        this.idLegalOwner = idLegalOwner;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getQtyDaysAsOwner() {
        return qtyDaysAsOwner;
    }

    public void setQtyDaysAsOwner(int qtyDaysAsOwner) {
        this.qtyDaysAsOwner = qtyDaysAsOwner;
    }

    public Set<PropertyContract> getPropertyContracts() {
        return propertyContracts;
    }

    public void addContracts(PropertyContract propertyContract) {
        propertyContracts.add(propertyContract);
        propertyContract.setOwner(this);
    }

    public void removePropertyContract(PropertyContract propertyContract) {
        propertyContracts.remove(propertyContract);
        propertyContract.setOwner(null);
    }

    @Override
    public String toString() {
        return "Owner{" +
                super.toString().replace("}", "") +
                ", isActive=" + isActive +
                ", isBusiness=" + isBusiness +
                ", idLegalOwner='" + idLegalOwner + '\'' +
                ", registrationDate=" + registrationDate +
                ", qtyDaysAsOwner=" + qtyDaysAsOwner +
                '}';
    }
}
