package com.openclassrooms.safetynetsalertsprojects.repository;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationsRepository {
    private static final Logger logger = LogManager.getLogger(FireStationsRepository.class);
    @Autowired
    private  DataSource dataSource;

    public List<FireStations> findAll(){
        logger.info("giving all firestations ok!");
        return dataSource.getFirestations();
    }

    public void save(FireStations fireStations) {
        List<FireStations> fireStationsList=findAll();
        fireStationsList.add(fireStations);
        logger.info("saving firestation ok!");
    }

    public void delete(FireStations fireStationsToDelete) {
        List<FireStations> fireStationsList = findAll();
        fireStationsList.remove(fireStationsToDelete);
        logger.info("deleting firestation ok!");
    }
}
