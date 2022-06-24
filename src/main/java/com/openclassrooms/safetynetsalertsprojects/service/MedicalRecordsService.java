package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import com.openclassrooms.safetynetsalertsprojects.repository.MedicalRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordsService {
    @Autowired
    MedicalRecordsRepository medicalRecordsRepository;

    public List<MedicalRecords> getMedicalRecordsList(){
        return medicalRecordsRepository.findAll();
    }
}
