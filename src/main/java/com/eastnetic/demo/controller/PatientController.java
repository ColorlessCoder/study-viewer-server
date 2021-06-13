package com.eastnetic.demo.controller;

import com.eastnetic.demo.entity.Patient;
import com.eastnetic.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("/picker")
    public List<Patient> getPatientForPicker(@RequestParam("query") String query, @RequestParam("limit") int limit) {
        return patientService.getPatientsForPicker(query, limit);
    }
}
