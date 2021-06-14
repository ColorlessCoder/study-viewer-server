package com.eastnetic.demo.service;

import com.eastnetic.demo.dao.PatientDAO;
import com.eastnetic.demo.entity.Patient;
import com.eastnetic.demo.util.MessageException;
import com.eastnetic.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class PatientService {
    @Autowired
    PatientDAO patientDAO;
    public List<Patient> getPatientsForPicker(String query, int limit) {
        return patientDAO.getPatientsForPicker("%" + query + "%", limit);
    }

    public void validatePatient(Patient patient) throws MessageException {
        if(StringUtils.isNullOrEmpty(patient.getPersonCode())) {
            throw new MessageException("Person code cannot be empty");
        }
        if(StringUtils.isNullOrEmpty(patient.getFirstName())) {
            throw new MessageException("First name cannot be empty");
        }
        if(StringUtils.isNullOrEmpty(patient.getLastName())) {
            throw new MessageException("Last name cannot be empty");
        }
        if(patient.getDateOfBirth() == null) {
            throw new MessageException("Date of Birth cannot be empty");
        }
        if(patient.getDateOfBirth().compareTo(new Date()) > 0) {
            throw new MessageException("Date of Birth cannot be in future.");
        }
        Patient existingPatient = patientDAO.getByPersonCode(patient.getPersonCode());
        if(existingPatient != null && existingPatient.getPatientPk() != existingPatient.getPatientPk()) {
            throw new MessageException("A patient with same Person code already exists.");
        }
    }

    public void trimWhiteSpace(Patient patient) {
        patient.setFirstName(StringUtils.nullSafeTrim(patient.getFirstName()));
        patient.setLastName(StringUtils.nullSafeTrim(patient.getLastName()));
        patient.setPersonCode(StringUtils.nullSafeTrim(patient.getPersonCode()));
    }
}
