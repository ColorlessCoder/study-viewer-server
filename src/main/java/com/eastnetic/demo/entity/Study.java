package com.eastnetic.demo.entity;

import java.time.LocalDateTime;

public class Study implements BaseEntity {
    private long studyPk;
    private String studyName;
    private String studyDescription;
    private LocalDateTime modifiedAt;
    private Patient patient;

    public Study() {}
    public Study(String studyName, String studyDescription, LocalDateTime modifiedAt, Patient patient) {
        this.studyName = studyName;
        this.studyDescription = studyDescription;
        this.modifiedAt = modifiedAt;
        this.patient = patient;
    }

    public long getStudyPk() {
        return studyPk;
    }

    public void setStudyPk(long studyPk) {
        this.studyPk = studyPk;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public String getStudyDescription() {
        return studyDescription;
    }

    public void setStudyDescription(String studyDescription) {
        this.studyDescription = studyDescription;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(studyPk);
    }
}
