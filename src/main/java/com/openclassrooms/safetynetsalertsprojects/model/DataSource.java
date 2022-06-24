package com.openclassrooms.safetynetsalertsprojects.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class DataSource {
    @Autowired
    ObjectMapper mapper;
    String JSONPATH ="src/main/resources/json/jsondata";

    private List<Persons> persons ;
    private List<FireStations> firestations;
    private List<MedicalRecords> medicalrecords;

    public List<Persons> getPersons() {
        return persons;
    }

    public void setPersons(List<Persons> persons) {
        this.persons = persons;
    }

    public List<FireStations> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<FireStations> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecords> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }

@PostConstruct
    public void init() throws IOException {

        DataSource dataSource = mapper.readValue(new File(JSONPATH), DataSource.class);
        this.persons = dataSource.getPersons();
        this.firestations = dataSource.getFirestations();
        this.medicalrecords = dataSource.getMedicalrecords();
    }
}

