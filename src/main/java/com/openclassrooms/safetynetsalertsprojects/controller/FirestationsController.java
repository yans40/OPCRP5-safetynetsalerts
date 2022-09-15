package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.FirestationsDto;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FirestationsController {

    @Autowired
    private FireStationsService fireStationsService;


    @GetMapping("/getfirestations")
    public List<FirestationsDto> showFireStationsList() {

        return fireStationsService.getFireStationsList();
    }

    @GetMapping("/stationNumber")
    public List<FirestationsDto> findFirestationByStationNumber(@RequestParam String station) {

        return fireStationsService.getFirestationByStationNumber(station);

    }

    @GetMapping("/firestations")
    public Map<String, String> getfirestationsmap() {
        return fireStationsService.getFireStationsHashMap();
    }


    @PostMapping("/firestation")
    public void addNewFirestationInTheMap(@RequestBody FirestationsDto firestationsDto) {
        fireStationsService.addNewFirestation(firestationsDto);
    }

    @PutMapping("/firestations/update/{address}")
    public void updateFirestation(@RequestBody FirestationsDto firestationsDto, @PathVariable String address) {
        fireStationsService.updateFirestation(address, firestationsDto);
    }

    @DeleteMapping("/firestations/delete/{address}")
    public void deleteFirestation(@PathVariable String address) {
        fireStationsService.delete(address);
    }

}
