package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class MedicalRecordsController {

    private static final Logger logger = LogManager.getLogger(MedicalRecordsController.class);
    @Autowired
    private MedicalRecordsService medicalRecordsService;

    @GetMapping("/medicalrecords")
    public List<MedicalRecordsDto> getAllMedicalRecords() {
        logger.info("all medicalRecords!");
        return medicalRecordsService.getMedicalRecordsList();
    }

    @PostMapping("/medicalrecord")
    public void addNewMedicalRecord(@RequestBody MedicalRecordsDto medicalRecordsDto) {
        logger.info("add a new MedicalRecords!");
        medicalRecordsService.addNewMedicalRecord(medicalRecordsDto);
    }


    @PutMapping("/medicalrecords/update/{firstName}&{lastName}")
    public void updateMedicalRecord(@RequestBody MedicalRecordsDto medicalRecordsDto, @PathVariable String firstName, String lastName) {
        logger.info("up dating an existing Medical Records!");
        medicalRecordsService.updateMedicalRecord(firstName, lastName, medicalRecordsDto);
    }

    @DeleteMapping("/medicalrecords/delete/{firstName}&{lastName}")
    public void deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("deleting a Medical Records ! ");
        medicalRecordsService.deleteMedicalRecord(firstName, lastName);
    }
}
