package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FirestationsController {

    @Autowired
    private FireStationsService fireStationsService;


    @GetMapping
    public List<FireStations> showFireStationsList() {

        return fireStationsService.getFireStationsList();
    }

    @GetMapping("/station")
    public List<FireStations> findFirestationByStationNumber(@RequestParam String station) {

       return fireStationsService.getFirestationByStationNumber(station);

    }

    @GetMapping("/hello")
    public String greetings(@RequestParam String name){
  String discours = "hello "+ name;
        return discours  ;
    }
}
