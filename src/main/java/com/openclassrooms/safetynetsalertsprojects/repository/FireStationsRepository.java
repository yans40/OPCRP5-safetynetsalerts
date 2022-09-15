package com.openclassrooms.safetynetsalertsprojects.repository;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationsRepository {
    @Autowired
    private  DataSource dataSource;

    public List<FireStations> findAll(){
        return dataSource.getFirestations();
    }

    public void save(FireStations fireStations) {
        List<FireStations> fireStationsList=findAll();
        fireStationsList.add(fireStations);
    }

    public void delete(FireStations fireStationsToDelete) {
        List<FireStations> fireStationsList = findAll();
        fireStationsList.remove(fireStationsToDelete);
    }
}
