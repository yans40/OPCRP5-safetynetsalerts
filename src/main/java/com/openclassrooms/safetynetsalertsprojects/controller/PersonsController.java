package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.*;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController

public class PersonsController {
    private static final Logger logger = LogManager.getLogger(PersonsController.class);
    @Autowired
    private PersonsService personsService;
    @Autowired
    private FireStationsService fireStationsService;
    @Autowired
    private MedicalRecordsService medicalRecordsService;


    @GetMapping("/persons")
    public List<FirestationByStationNumberDto> showPersonsList() {
        logger.info("list of persons By stationNumber");
        return personsService.getPersonList();
    }

    @GetMapping("/firestation")
    public FirestationByStationNumberParentDto personInPotentialRisk(@RequestParam String station) throws ParseException {
        logger.info("personInPotentialRisk where stationNumber is!");
        return personsService.getFirestationByStationNumberDto(station, fireStationsService, medicalRecordsService);
    }

    @GetMapping("/childAlert")
    public ChildAlertListAndFamilyDto findChildAndFamily(@RequestParam String address) throws ParseException {
        logger.info("find a list of children By address and their family !");
        List<FirestationByStationNumberDto> personsByAddressList = personsService.getPersonsByAddress(address);
        return personsService.getChildAlertListAndFamilyDto(address, personsByAddressList, medicalRecordsService);
    }

    @GetMapping("/phoneAlert")
    public List<PhoneListDto> phoneAlertListByStation(@RequestParam String firestation) {
        logger.info("find list of phoneNumber where firestation is .. !");
        List<FirestationsDto> fireStationsList = fireStationsService.getFirestationByStationNumber(firestation);
        List<FirestationByStationNumberDto> listOfPersons = personsService.getPersonList();
        return personsService.getPhoneListDtos(fireStationsList, listOfPersons);

    }

    @GetMapping("/fire")
    public List<FireByAddressDto> fireByAddressList(String address) throws Exception {
        logger.info("find a List of persons giving an address where fire is declared!");
        return personsService.getFireByAddressDtos(address);
    }

    @GetMapping("/communityEmail")
    public List<CommunityEmailByCityDto> emailByCity(@RequestParam String city) {
        logger.info("the list of mail of this city");
        return personsService.emailListByCity(city);
    }

    @GetMapping("/flood/stations")
    public List<FloodByListOfStationDto> floodListByStation(@RequestParam List<String> stations) throws ParseException {
        logger.info("all family deserve by a firestation!");
        return personsService.getFloodByListOfStationDtos(stations);
    }


    @GetMapping("/personInfo")
    public List<PersonInfoByNameDto> personsListByFirstNameAndLastName(@RequestParam String firstName, String lastName) throws ParseException {
        logger.info("the list of medicals Informations by Names");
        return personsService.getPersonInfoByNameDtos(firstName, lastName);
    }


    @PostMapping("/person")
    public void addNewPerson(@RequestBody PersonDto personDto) {
        logger.info("adding a new Person");
        personsService.addNewPerson(personDto);
    }

    @PutMapping("/persons/update/{firstName}&{lastName}")
    public void updatePersonInfo(@RequestBody PersonDto personDto, @PathVariable String firstName, @PathVariable String lastName) {
        logger.info("updating an existing person's informations!");
        personsService.updatePerson(firstName, lastName, personDto);
    }

    @DeleteMapping("/persons/delete/{firstName}&{lastName}")
    public void deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("delete a person!");
        personsService.deletePerson(firstName, lastName);
    }
}
