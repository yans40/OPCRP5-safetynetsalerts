package com.openclassrooms.safetynetsalertsprojects.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DataSourceController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/persons")
    public List<Persons> showPersonsList() {
        return dataSource.getPersons();

    }

    @GetMapping("/medicalrecords")
    public List<MedicalRecords> showMedicalRecordList() {
        return dataSource.getMedicalrecords();
    }

    @GetMapping("/firestations")
    public List<FireStations> showFireStationsList() {
        return dataSource.getFirestations();
    }


}



