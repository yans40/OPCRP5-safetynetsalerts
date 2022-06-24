package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.repository.FireStationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationsService {
    @Autowired
    FireStationsRepository fireStationsRepository;

    public List<FireStations> getFireStationsList(){

        return fireStationsRepository.findAll();
    }
}
