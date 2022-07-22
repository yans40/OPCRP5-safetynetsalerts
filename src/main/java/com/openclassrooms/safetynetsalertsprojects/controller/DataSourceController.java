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
    public FirestationByStationNumberParentDto personInPotentialRisk(@RequestParam String station) throws ParseException {

        List<FirestationsDto> fireStationsByNumber = fireStationsService.getFirestationByStationNumber(station);
        List<FirestationByStationNumberDto> personList = personsService.getPersonList();
        List<MedicalRecordsDto> medicalRecordsList = medicalRecordsService.getMedicalRecordsList();
        List<FirestationByStationNumberDto> alertPersonsList = new ArrayList<>();
        int nbOfAdult = 0;
        int nbOfChild = 0;

        for (FirestationsDto fireStation : fireStationsByNumber) {
            for (FirestationByStationNumberDto person : personList) {

                if (fireStation.getAddress().equals(person.getAddress())) {
                    alertPersonsList.add(person);
                    for (MedicalRecordsDto pers : medicalRecordsList) {
                        if (pers.getLastName().equals(person.getLastName()) && pers.getFirstName().equals(person.getFirstName())) {
                            Date persAge = medicalRecordsService.birthdayStringToDate(pers.getBirthdate());
                            int agePers = medicalRecordsService.ageCalculator(persAge);

                            if (agePers >= 18) {
                                nbOfAdult++;
                            } else {
                                nbOfChild++;
                            }
                        }
                    }
                }
            }
        }
        FirestationByStationNumberParentDto firestationByStationNumberParentDto = new FirestationByStationNumberParentDto();
        firestationByStationNumberParentDto.setPersonsInfo(alertPersonsList);
        firestationByStationNumberParentDto.setNbAdult(nbOfAdult);
        firestationByStationNumberParentDto.setNbChild(nbOfChild);
        return firestationByStationNumberParentDto;
    }

    @GetMapping("/childAlert")
    public ChildAlertListAndFamilyDto findChildAndFamily(@RequestParam String address) throws ParseException {

        List<FirestationByStationNumberDto> personsByAddressList = personsService.getPersonsByAddress(address);
        List<MedicalRecordsDto> medicalRecordsDtoList = medicalRecordsService.getMedicalRecordsList();
        List<ChildAlertByAddressDto> listOfChildByAddress = new ArrayList<>();
        List<ParentListByAdressDto> listOfParentByAddress = new ArrayList<>();

        for (FirestationByStationNumberDto person : personsByAddressList) {
            for (MedicalRecordsDto pers : medicalRecordsDtoList) {

                if (person.getFirstName().equals(pers.getFirstName()) && person.getLastName().equals(pers.getLastName())) {
                    String dateNaissance = pers.getBirthdate();
                    Date persAge = medicalRecordsService.birthdayStringToDate(dateNaissance);
                    int age = medicalRecordsService.ageCalculator(persAge);
                    if (age <= 18) {
                        ChildAlertByAddressDto childAlertByAddressDto = new ChildAlertByAddressDto();
                        childAlertByAddressDto.setFirstName(pers.getFirstName());
                        childAlertByAddressDto.setLastName(pers.getLastName());
                        childAlertByAddressDto.setAge(age);
                        listOfChildByAddress.add(childAlertByAddressDto);
                    }
                }
            }
        }
        for (ChildAlertByAddressDto child : listOfChildByAddress) {
            for (FirestationByStationNumberDto person : personsByAddressList) {

                if (child.getLastName().equals(person.getLastName()) && address.equals(person.getAddress())) {
                    ParentListByAdressDto parentListByAdressDto = new ParentListByAdressDto();
                    parentListByAdressDto.setFirstName(person.getFirstName());
                    parentListByAdressDto.setLastName(person.getLastName());
                    listOfParentByAddress.add(parentListByAdressDto);
                }
            }
        }
        ChildAlertListAndFamilyDto childAlertListAndFamilyDto = new ChildAlertListAndFamilyDto();
        childAlertListAndFamilyDto.setChildAlertByAdressDtoList(listOfChildByAddress);
        childAlertListAndFamilyDto.setParentListByAdressDtoList(listOfParentByAddress);
        return childAlertListAndFamilyDto;
    }

    @GetMapping("/phoneAlert")
    public List<PhoneListDto> phoneAlertListByStation(@RequestParam String station) {

        List<FirestationsDto> fireStationsList = fireStationsService.getFirestationByStationNumber(station);
        List<FirestationByStationNumberDto> listOfPersons = personsService.getPersonList();
        List<PhoneListDto> listOfPhoneDtos = new ArrayList<>();

        for (FirestationsDto firestationsDto : fireStationsList) {
            for (FirestationByStationNumberDto person : listOfPersons) {

                if (firestationsDto.getAddress().equals(person.getAddress())) {
                    PhoneListDto phoneListDto = new PhoneListDto();
                    phoneListDto.setPhone(person.getPhone());
                    listOfPhoneDtos.add(phoneListDto);
                }
            }
        }
        return listOfPhoneDtos;
    }

    @GetMapping("/fire")
    public List<FireByAddressDto> fireByAddressList(String address) throws Exception {

        List<FirestationByStationNumberDto> personsByAddressList = personsService.getPersonsByAddress(address);
        List<MedicalRecordsDto> medicalRecordsDtoList = medicalRecordsService.getMedicalRecordsList();
        List<FireByAddressDto> listFireByAddress = new ArrayList<>();

        for (FirestationByStationNumberDto person : personsByAddressList) {
            for (MedicalRecordsDto pers : medicalRecordsDtoList) {

                if ((pers.getLastName().equals(person.getLastName()) && pers.getFirstName().equals(person.getFirstName()))) {
                    String dateNaissance = pers.getBirthdate();
                    Date persAge = medicalRecordsService.birthdayStringToDate(dateNaissance);
                    int age = medicalRecordsService.ageCalculator(persAge);
                    FireByAddressDto fireByAddressDto = new FireByAddressDto();
                    fireByAddressDto.setFirstName(person.getFirstName());
                    fireByAddressDto.setLastName(pers.getLastName());
                    fireByAddressDto.setPhone(person.getPhone());
                    fireByAddressDto.setMedications(pers.getMedications());
                    fireByAddressDto.setAllergies(pers.getAllergies());
                    fireByAddressDto.setAge(age);
                    listFireByAddress.add(fireByAddressDto);
                }
            }
        }
        return listFireByAddress;
    }

    @GetMapping("/flood/stations")
    public List<FloodByListOfStationDto> floodListByStation(@RequestParam List<String> stations) throws ParseException {

        List<FloodByListOfStationDto> floodByListOfStationDtoList = new ArrayList<>();

        for(String station:stations) {
        List<FirestationsDto> firestationsDtoList = fireStationsService.getFirestationByStationNumber(station);
        List<MedicalRecordsDto> medicalRecordsDtoList = medicalRecordsService.getMedicalRecordsList();


        for (FirestationsDto firestationsDto : firestationsDtoList) {
            List<FirestationByStationNumberDto> firestation = personsService.getPersonsByAddress(firestationsDto.getAddress());
            for (FirestationByStationNumberDto fs : firestation) {

                if (firestationsDto.getAddress().equals(fs.getAddress())) {
                    List<FirestationByStationNumberDto> listPersonByAddress = new ArrayList<>();
                    listPersonByAddress.add(fs);
                    for (FirestationByStationNumberDto person : listPersonByAddress) {
                        for (MedicalRecordsDto medicalRecordsDto : medicalRecordsDtoList) {

                            if (person.getLastName().equals(medicalRecordsDto.getLastName()) && person.getFirstName().equals(medicalRecordsDto.getFirstName())) {
                                String dateNaissance = medicalRecordsDto.getBirthdate();
                                Date persAge = medicalRecordsService.birthdayStringToDate(dateNaissance);
                                int age = medicalRecordsService.ageCalculator(persAge);
                                FloodByListOfStationDto floodByListOfStationDto = new FloodByListOfStationDto();
                                floodByListOfStationDto.setLastName(medicalRecordsDto.getLastName());
                                floodByListOfStationDto.setPhone(person.getPhone());
                                floodByListOfStationDto.setAge(age);
                                floodByListOfStationDto.setAllergies(medicalRecordsDto.getAllergies());
                                floodByListOfStationDto.setMedications(medicalRecordsDto.getMedications());
                                floodByListOfStationDtoList.add(floodByListOfStationDto);
                            }
                        }
                    }
                }
            }
        }
        }
        return floodByListOfStationDtoList;
    }

    @GetMapping("/personInfo")
    public List<PersonInfoByNameDto> personsListByFirstNameandLastName(String firstName, String lastName) throws ParseException {

        List<PersonInfoByNameDto> personInfoByNameDtoList = personsService.getPersonByName(firstName, lastName);
        List<MedicalRecordsDto> medicalRecordsDtoList = medicalRecordsService.getMedicalRecordsList();
        List<PersonInfoByNameDto> listByNameDtoList = new ArrayList<>();

        for (PersonInfoByNameDto persons : personInfoByNameDtoList) {
            for (MedicalRecordsDto pers : medicalRecordsDtoList) {

                if (persons.getFirstName().equals(pers.getFirstName()) && persons.getLastName().equals(pers.getLastName())) {
                    String dateNaissance = pers.getBirthdate();
                    Date persAge = medicalRecordsService.birthdayStringToDate(dateNaissance);
                    int age = medicalRecordsService.ageCalculator(persAge);
                    PersonInfoByNameDto personInfoByNameDto = new PersonInfoByNameDto();
                    personInfoByNameDto.setFirstName(pers.getFirstName());
                    personInfoByNameDto.setLastName(pers.getLastName());
                    personInfoByNameDto.setAddress(persons.getAddress());
                    personInfoByNameDto.setAge(age);
                    personInfoByNameDto.setEmail(persons.getEmail());
                    personInfoByNameDto.setMedications(pers.getMedications());
                    personInfoByNameDto.setAllergies(pers.getAllergies());
                    listByNameDtoList.add(personInfoByNameDto);
                }

            }
        }
        return listByNameDtoList;
    }

    @GetMapping("/communityEmail")
    public List<CommunityEmailByCityDto> EmailByCity(@RequestParam String city) {
        return personsService.emailListByCity(city);
    }
}







