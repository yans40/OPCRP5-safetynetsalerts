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
    List<FirestationByStationNumberDto> personInPotentialRisk(@RequestParam String station) {

        List<FirestationsDto> fireStationsByNumber = fireStationsService.getFirestationByStationNumber(station);
        List<FirestationByStationNumberDto> personList = personsService.getPersonList();
        List<MedicalRecordsDto> medicalRecordsList = medicalRecordsService.getMedicalRecordsList();//TODO comment récupérer les infos date de naissance afin de calculer l'age
        List<FirestationByStationNumberDto> alertPersonsList = new ArrayList<>();

        for (FirestationsDto fireStation : fireStationsByNumber) {
            for (FirestationByStationNumberDto person : personList) {
                if (fireStation.getAddress().contentEquals(person.getAddress())) {
                    alertPersonsList.add(person);
                }
            }
        }
        return alertPersonsList;
    }


    @GetMapping("/childAlert")
    public List<FirestationByStationNumberDto> alertChildList(@RequestParam String address) throws ParseException {

        List<FirestationByStationNumberDto> personsByAddressList = personsService.getPersonsByAddress(address);
        List<MedicalRecordsDto> medicalRecordsList = medicalRecordsService.getMedicalRecordsList();
        List<FirestationByStationNumberDto> childList = new ArrayList<>();
        List<FirestationByStationNumberDto> childFamilyList = new ArrayList<>();

        for (FirestationByStationNumberDto person : personsByAddressList) {
            for (MedicalRecordsDto medicalRecordsPersondto : medicalRecordsList) {

                if (person.getFirstName().contentEquals(medicalRecordsPersondto.getFirstName())) {
                    String dateNaissance = medicalRecordsPersondto.getBirthdate();
                    Date persAge = medicalRecordsService.birthdayStringToDate(dateNaissance);
                    int age = medicalRecordsService.ageCalculator(persAge);
                    if (age < 18) childList.add(person);
                }
            }
        }
        for (FirestationByStationNumberDto child : childList) {
            String familyName = child.getLastName();
            String familyAddress = child.getAddress();
            for (FirestationByStationNumberDto persons : personsByAddressList)
                if ((familyName + familyAddress).contentEquals(persons.getLastName() + persons.getAddress())) {
                    childFamilyList.add(persons);
                }
        }
        return childFamilyList;
    }


    @GetMapping("/phoneAlert")
    public List<FirestationByStationNumberDto> phoneAlertListByStation(@RequestParam String station) {

        List<FirestationsDto> fireStationsList = fireStationsService.getFirestationByStationNumber(station);
        List<FirestationByStationNumberDto> phoneAlertList = new ArrayList<>();

        for (FirestationsDto fireStations : fireStationsList) {
            List<FirestationByStationNumberDto> personsList = personsService.getPersonsByAddress(fireStations.getAddress());
            for (FirestationByStationNumberDto persons : personsList) {
                if (fireStations.getAddress().contentEquals(persons.getAddress())) {
                    phoneAlertList.add(persons);
                }
            }
        }
        return phoneAlertList;
    }

    @GetMapping ("/personInfo")
    public List<PersonInfoByNameDto> personsListByFirstNameandLastName(String FirstName, String LastName){
      List<PersonInfoByNameDto> personInfoByNameDtoList = new ArrayList<>();//ne fait rien

        return personInfoByNameDtoList;
    }


    @GetMapping("community")
    public List<CommunityEmailByCityDto> EmailByCity(@RequestParam String city){

       return personsService.emailListByCity(city);
    }
}







