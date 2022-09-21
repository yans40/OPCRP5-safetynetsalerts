package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.FirestationsDto;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FirestationsController {

    private static final Logger logger = LogManager.getLogger(FirestationsController.class);
    @Autowired
    private FireStationsService fireStationsService;


    @GetMapping("/getfirestations")
    public List<FirestationsDto> showFireStationsList() {
        logger.info("all firestations!");
        return fireStationsService.getFireStationsList();
    }

    @GetMapping("/stationNumber")
    public List<FirestationsDto> findFirestationByStationNumber(@RequestParam String station) {
        logger.info("firestations By Number!");
        return fireStationsService.getFirestationByStationNumber(station);

    }

    @GetMapping("/firestations")
    public Map<String, String> getfirestationsmap() {
        logger.info("firestations mapping!");
        return fireStationsService.getFireStationsHashMap();
    }


    @PostMapping("/firestation")
    public void addNewFirestationInTheMap(@RequestBody FirestationsDto firestationsDto) {
        logger.info("adding a new firestations!");
        fireStationsService.addNewFirestation(firestationsDto);
    }

    @PutMapping("/firestations/update/{address}")
    public void updateFirestation(@RequestBody FirestationsDto firestationsDto, @PathVariable String address) {
        logger.info("updating an existing firestation!");
        fireStationsService.updateFirestation(address, firestationsDto);
    }

    @DeleteMapping("/firestations/delete/{address}")
    public void deleteFirestation(@PathVariable String address) {
        logger.info("deleting a firestation!");
        fireStationsService.delete(address);
    }

}
