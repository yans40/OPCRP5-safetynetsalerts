package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.FirestationsDto;
import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.dto.PersonsDto;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class DataSourceController {

    @Autowired
    private PersonsService personsService;
    @Autowired
    private FireStationsService fireStationsService;
    @Autowired
    private MedicalRecordsService medicalRecordsService;

    @GetMapping("/firestation")
    List<PersonsDto> personInPotentialRisk(@RequestParam String station) {

        List<FirestationsDto> fireStationsByNumber = fireStationsService.getFirestationByStationNumber(station);
        List<PersonsDto> personList = personsService.getPersonList();
        List<MedicalRecordsDto> medicalRecordsList = medicalRecordsService.getMedicalRecordsList();//TODO comment récupérer les infos date de naissance afin de calculer l'age
        List<PersonsDto> alertPersonsList = new ArrayList<>();

        for (FirestationsDto fireStation : fireStationsByNumber) {
            for (PersonsDto person : personList) {
                if (fireStation.getAddress().contentEquals(person.getAddress())) {
                    alertPersonsList.add(person);
                }
            }
        }
        return alertPersonsList;
    }


    @GetMapping("/childAlert")
    public List<PersonsDto> alertChildList(@RequestParam String address) throws ParseException {

        List<PersonsDto> personsByAddressList = personsService.getPersonsByAddress(address);
        List<MedicalRecordsDto> medicalRecordsList = medicalRecordsService.getMedicalRecordsList();
        List<PersonsDto> childList = new ArrayList<>();
        List<PersonsDto> childFamilyList = new ArrayList<>();

        for (PersonsDto person : personsByAddressList) {
            for (MedicalRecordsDto medicalRecordsPerson : medicalRecordsList) {

                if (person.getFirstName().contentEquals(medicalRecordsPerson.getFirstName())) {
                    String dateNaissance = medicalRecordsPerson.getBirthdate();
                    Date persAge = medicalRecordsService.birthdayStringToDate(dateNaissance);
                    int age = medicalRecordsService.ageCalculator(persAge);
                    if (age < 18) childList.add(person);
                }
            }
        }
        for (PersonsDto child : childList) {
            String familyName = child.getLastName();
            String familyAdress = child.getAddress();
            for (PersonsDto persons : personsByAddressList)
                if ((familyName + familyAdress).contentEquals(persons.getLastName() + persons.getAddress())) {
                    childFamilyList.add(persons);
                }
        }
        return childFamilyList;
    }


    @GetMapping("/phoneAlert")
    public List<PersonsDto> phoneAlertListByStation(@RequestParam String station) {

        List<FirestationsDto> fireStationsList = fireStationsService.getFirestationByStationNumber(station);
        List<PersonsDto> phoneAlertList = new ArrayList<>();

        for (FirestationsDto fireStations : fireStationsList) {
            List<PersonsDto> personsList = personsService.getPersonsByAddress(fireStations.getAddress());
            for (PersonsDto persons : personsList) {
                if (fireStations.getAddress().contentEquals(persons.getAddress())) {
                    phoneAlertList.add(persons);
                }
            }
        }
        return phoneAlertList;
    }
}







