package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.dto.PersonDto;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class MedicalRecordsController {

    @Autowired
    private MedicalRecordsService medicalRecordsService;

    @GetMapping("/medicalrecords")
    public List<MedicalRecordsDto> getAllMedicalRecords() {
        return medicalRecordsService.getMedicalRecordsList();
    }

    @PostMapping("/medicalrecord")
    public void addNewMedicalRecord(@RequestBody MedicalRecordsDto medicalRecordsDto){
        medicalRecordsService.addNewMedicalRecord(medicalRecordsDto);
    }


    @PutMapping("/medicalrecords/update/{firstName}&{lastName}")
    public void updateMedicalRecord(@RequestBody MedicalRecordsDto medicalRecordsDto, @PathVariable String firstName, String lastName) {
        medicalRecordsService.updateMedicalRecord(firstName,lastName, medicalRecordsDto);
    }

}
