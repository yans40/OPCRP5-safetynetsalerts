package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.*;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
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













}







