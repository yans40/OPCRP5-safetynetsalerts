package com.openclassrooms.safetynetsalertsprojects.repository;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordsRepository {

    private static final Logger logger = LogManager.getLogger(MedicalRecordsRepository.class);
    @Autowired
    private DataSource dataSource;

    public List<MedicalRecords> findAll() {
        logger.info("giving all MedicalRecords ok!");
        return dataSource.getMedicalrecords();
    }

    public void save(MedicalRecords medicalRecords) {
        List<MedicalRecords> medicalRecordsList = findAll();
        medicalRecordsList.add(medicalRecords);
        logger.info("saving MedicalRecords ok!");
    }

    public void update(int index, MedicalRecords medicalRecords) {
        List<MedicalRecords> list = findAll();
        list.set(index, medicalRecords);
        logger.info("updating MedicalRecords ok!");
    }

    public void delete(MedicalRecords medicalRecordTodelete) {
        List<MedicalRecords> medicalRecordsList = findAll();
        medicalRecordsList.remove(medicalRecordTodelete);
        logger.info("deleting MedicalRecords ok!");
    }
}
