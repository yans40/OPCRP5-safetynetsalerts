package com.openclassrooms.safetynetsalertsprojects.repository;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class MedicalRecordsRepository {
    @Autowired
    private DataSource dataSource;

    public List<MedicalRecords> findAll(){

        return dataSource.getMedicalrecords();
    }

    public void save(MedicalRecords medicalRecords) {
        List<MedicalRecords> medicalRecordsList=findAll();
        medicalRecordsList.add(medicalRecords);
    }

}
