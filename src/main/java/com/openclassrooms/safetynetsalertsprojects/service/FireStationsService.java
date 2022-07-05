package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.repository.FireStationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationsService {
    @Autowired
    FireStationsRepository fireStationsRepository;

    public List<FireStations> getFireStationsList() {

        return fireStationsRepository.findAll();
    }

    public List<FireStations> getFirestationByStationNumber(@RequestParam String station ) {

        List<FireStations> fireStations = getFireStationsList();
        List<FireStations> fireStationsFindByNumber = new ArrayList<>();

        for (FireStations fs : fireStations) {

            if (fs.getStation().contentEquals(station)) {

                fireStationsFindByNumber.add(fs);

            }

        }
        return fireStationsFindByNumber;
    }
}
