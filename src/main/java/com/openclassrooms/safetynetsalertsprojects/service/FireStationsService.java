package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.dto.FirestationsDto;
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
    private FireStationsRepository fireStationsRepository;


    public List<FirestationsDto> getFireStationsList() {

        List<FireStations> fireStationsList = fireStationsRepository.findAll();
        List<FirestationsDto> firestationsDtoList = new ArrayList<>();

        for (FireStations fireStations : fireStationsList){
            FirestationsDto firestationsDto = new FirestationsDto("address","station");
            firestationsDto.setAddress(fireStations.getAddress());
            firestationsDto.setStation(fireStations.getStation());
            firestationsDtoList.add(firestationsDto);

        }
        return firestationsDtoList;
    }

    public List<FirestationsDto> getFirestationByStationNumber(@RequestParam String station ) {

        List<FirestationsDto> fireStations = getFireStationsList();
        List<FirestationsDto> fireStationsFindByNumber = new ArrayList<>();

        for (FirestationsDto fs : fireStations) {

            if (fs.getStation().contentEquals(station)) {

                fireStationsFindByNumber.add(fs);

            }

        }
        return fireStationsFindByNumber;
    }
}
