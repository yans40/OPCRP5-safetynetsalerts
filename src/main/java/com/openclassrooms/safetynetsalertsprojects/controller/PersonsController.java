package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.*;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController

public class PersonsController {

    @Autowired
    private PersonsService personsService;
    @Autowired
    private FireStationsService fireStationsService;
    @Autowired
    private MedicalRecordsService medicalRecordsService;


    @GetMapping("/persons")
    public List<FirestationByStationNumberDto> showPersonsList() {

        return personsService.getPersonList();
    }

    @GetMapping ("/address")
    public List<FirestationByStationNumberDto> findPersonsByAddress(@RequestParam String address){

        return personsService.getPersonsByAddress(address);

    }
    @GetMapping("/firestation")
    public FirestationByStationNumberParentDto personInPotentialRisk(@RequestParam String station) throws ParseException {

        return personsService.getFirestationByStationNumberDto(station, fireStationsService, medicalRecordsService);
    }

    @GetMapping("/childAlert")
    public ChildAlertListAndFamilyDto findChildAndFamily(@RequestParam String address) throws ParseException {

        List<FirestationByStationNumberDto> personsByAddressList = personsService.getPersonsByAddress(address);
        return personsService.getChildAlertListAndFamilyDto(address, personsByAddressList, medicalRecordsService);
    }

    @GetMapping("/phoneAlert")
    public List<PhoneListDto> phoneAlertListByStation(@RequestParam String firestation) {

        List<FirestationsDto> fireStationsList = fireStationsService.getFirestationByStationNumber(firestation);
        List<FirestationByStationNumberDto> listOfPersons = personsService.getPersonList();
        return personsService.getPhoneListDtos(fireStationsList, listOfPersons);

    }
    @GetMapping("/fire")
    public List<FireByAddressDto> fireByAddressList(String address) throws Exception {

        return personsService.getFireByAddressDtos(address);
    }

    @GetMapping("/communityEmail")
    public List<CommunityEmailByCityDto> EmailByCity(@RequestParam String city) {
        return personsService.emailListByCity(city);
    }

    @GetMapping("/flood/stations")
    public List<FloodByListOfStationDto> floodListByStation(@RequestParam List<String> stations) throws ParseException {

        return personsService.getFloodByListOfStationDtos(stations);
    }


    @GetMapping("/personInfo")
    public List<PersonInfoByNameDto> personsListByFirstNameAndLastName(String firstName, String lastName) throws ParseException {

        return personsService.getPersonInfoByNameDtos(firstName, lastName);
    }
}
