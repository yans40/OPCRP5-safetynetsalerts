package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicalRecordsController {

    @Autowired
    private MedicalRecordsService medicalRecordsService;


    @GetMapping("/medicalrecords")
    public List<MedicalRecordsDto> showMedicalRecordList() {

        return medicalRecordsService.getMedicalRecordsList();
    }

    @GetMapping("/birthdays")

    public List<String> birthdaysList(){

        return medicalRecordsService.birthdays();
    }

}
