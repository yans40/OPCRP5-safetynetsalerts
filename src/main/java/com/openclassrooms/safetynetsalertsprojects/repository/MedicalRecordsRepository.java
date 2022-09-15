package com.openclassrooms.safetynetsalertsprojects.repository;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsRepository {
    @Autowired
    private DataSource dataSource;

    public List<MedicalRecords> findAll() {

        return dataSource.getMedicalrecords();
    }

    public void save(MedicalRecords medicalRecords) {
        List<MedicalRecords> medicalRecordsList = findAll();
        medicalRecordsList.add(medicalRecords);
    }

    public void update(int index, MedicalRecords medicalRecords) {
        List<MedicalRecords> list = findAll();
        list.set(index, medicalRecords);
    }

    public void delete(MedicalRecords medicalRecordTodelete) {
        List<MedicalRecords> medicalRecordsList = findAll();
        medicalRecordsList.remove(medicalRecordTodelete);
    }
}
