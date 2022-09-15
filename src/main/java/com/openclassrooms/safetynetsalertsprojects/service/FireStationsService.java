package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.dto.FirestationsDto;
import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.repository.FireStationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FireStationsService {
    @Autowired
    private FireStationsRepository fireStationsRepository;

    public void addNewFirestation(FirestationsDto firestationsDto) {
        FireStations fireStations = dtoToFireStations(firestationsDto);
        fireStationsRepository.save(fireStations);
    }

    public FireStations dtoToFireStations(FirestationsDto firestationsDto) {
        return new FireStations(firestationsDto.getAddress(), firestationsDto.getStation());
    }


    public void updateFirestation(String address, FirestationsDto firestationsDto) {
        FirestationsDto firestationsDto1 = findFirestationByAddress(address);
        settingFirestationChanges(firestationsDto1, firestationsDto);
        FireStations fireStations = dtoToFireStations(firestationsDto1);
        fireStationsRepository.save(fireStations);
    }

    private void settingFirestationChanges(FirestationsDto firestationsDto1, FirestationsDto firestationsDto) {
        firestationsDto1.setAddress(firestationsDto.getAddress());
        firestationsDto1.setStation(firestationsDto.getStation());
    }

    public FirestationsDto findFirestationByAddress(String address) {
        List<FireStations> fireStationsList = fireStationsRepository.findAll();
        FirestationsDto firestationsDto = new FirestationsDto();

        for (FireStations firestation : fireStationsList) {
            if (firestation.getAddress().equals(address)) {
                firestationsDto.setStation(firestation.getStation());
                firestationsDto.setAddress(firestation.getAddress());
            }
        }
        return firestationsDto;
    }

    public FireStations findFirestation(String address) {
        List<FireStations> fireStationsList = fireStationsRepository.findAll();

        for (FireStations firestation : fireStationsList) {
            if (firestation.getAddress().equals(address)) {
                return firestation;
            }
        }
        return null;
    }

    public List<FirestationsDto> getFireStationsList() {

        List<FireStations> fireStationsList = fireStationsRepository.findAll();
        List<FirestationsDto> firestationsDtoList = new ArrayList<>();

        for (FireStations fireStations : fireStationsList) {
            FirestationsDto firestationsDto = new FirestationsDto("address", "station");
            firestationsDto.setAddress(fireStations.getAddress());
            firestationsDto.setStation(fireStations.getStation());
            firestationsDtoList.add(firestationsDto);
        }
        return firestationsDtoList;
    }

    public List<FirestationsDto> getFirestationByStationNumber(String station) {

        List<FirestationsDto> fireStations = getFireStationsList();
        List<FirestationsDto> fireStationsFindByNumber = new ArrayList<>();

        for (FirestationsDto fs : fireStations) {

            if (fs.getStation().contentEquals(station)) {
                fireStationsFindByNumber.add(fs);
            }
        }
        return fireStationsFindByNumber;
    }

    public Map<String, String> getFireStationsHashMap() {

        List<FireStations> fireStationsList = fireStationsRepository.findAll();
        Map<String, String> firestationsMap = new HashMap<>();

        for (FireStations fireStations : fireStationsList) {
            firestationsMap.put(fireStations.getAddress(), fireStations.getStation());
        }
        return firestationsMap;
    }


    public void delete(String address) {
   FireStations fireStationsToDelete = findFirestation(address);

   fireStationsRepository.delete(fireStationsToDelete);

    }
}
