package com.openclassrooms.safetynetsalertsprojects.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DataSourceController {

    @Autowired
   private PersonsService personsService;
    @Autowired
    private FireStationsService fireStationsService;
    @Autowired
    private MedicalRecordsService medicalRecordsService;
//
//    @GetMapping("/persons")
//    public List<Persons> showPersonsList() {
//
//        return personsService.getPersonList();
//    }
//
//    @GetMapping("/medicalrecords")
//    public List<MedicalRecords> showMedicalRecordList() {
//
//        return medicalRecordsService.getMedicalRecordsList();
//    }
//
//    @GetMapping("/firestations")
//    public List<FireStations> showFireStationsList() {
//
//        return fireStationsService.getFireStationsList();
//    }

//    @GetMapping("/stationNumber")
//    public List<FireStations> getFireStationByIsStationNumber(@RequestParam String stationNumber) {
//
//
//                     List<FireStations> fireStationsFindByStationNumberList= fireStationsService.getFirestationByStationNumber(stationNumber);
//
//
//
//    }

}






