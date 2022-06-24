package com.openclassrooms.safetynetsalertsprojects.repository;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsRepository {
    @Autowired
    DataSource dataSource;

    public List<MedicalRecords> findAll(){

        return dataSource.getMedicalrecords();
    }
}
