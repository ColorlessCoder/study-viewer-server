package com.eastnetic.demo.entity;

import java.util.Date;

public class Patient implements BaseEntity {
    private long patientPk;
    private String personCode;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    public Patient() {}

    public Patient(String personCode, String firstName, String lastName, Date dateOfBirth) {
        this.personCode = personCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public long getPatientPk() {
        return patientPk;
    }

    public void setPatientPk(long patientPk) {
        this.patientPk = patientPk;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(patientPk);
    }
}
