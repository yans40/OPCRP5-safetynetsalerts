package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.dto.*;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.repository.PersonsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonsService {

    private static final Logger logger = LogManager.getLogger(PersonsService.class);
    @Autowired
    private PersonsRepository personsRepository;
    @Autowired
    FireStationsService fireStationsService;

    @Autowired
    MedicalRecordsService medicalRecordsService;


    public void addNewPerson(PersonDto personDto) {
        logger.info("adding new person !");
        Persons persons = dtoToPerson(personDto);
        personsRepository.save(persons);
    }

    public Persons dtoToPerson(PersonDto personDto) {
        logger.info("transforming dto to persons !");
        return new Persons(personDto.getFirstName(), personDto.getLastName(), personDto.getAddress(), personDto.getCity(), personDto.getPhone(), personDto.getZip(), personDto.getEmail());
    }


    public void updatePerson(String firstName, String lastName, PersonDto personDto) {
        logger.info("updating in progress ...!");
        PersonDto personDto1 = findPersonByFirstNameAndLastName(firstName, lastName);
        settingPersonDtoChanges(personDto1, personDto.getFirstName(), personDto.getLastName(), personDto.getAddress(), personDto.getCity(), personDto.getPhone(), personDto.getEmail(), personDto.getZip());
        Persons persons = dtoToPerson(personDto1);
        int index = getIndexOfPersons(personDto1);
        personsRepository.update(index, persons);
    }

    public void deletePerson(String firstName, String lastName) {
        logger.info("deleting in progress ...!");
        Persons personsToDelete = findPerson(firstName, lastName);
        if (personsToDelete != null) {
            personsRepository.delete(personsToDelete);
        }
    }

    public int getIndexOfPersons(PersonDto personDto) {
        logger.info("index catching!");
        List<Persons> list = personsRepository.findAll();
        int index = 0;
        for (Persons pers : list) {
            if (pers.getFirstName().equals(personDto.getFirstName()) && pers.getLastName().equals(personDto.getLastName())) {
                logger.info("in the if !");
                index = list.lastIndexOf(pers);
            }
        }
        return index;

    }

    public void settingPersonDtoChanges(PersonDto personDto1, String firstName2, String lastName2, String address, String city, String phone, String email, String zip) {
        logger.info("setting changes !");
        personDto1.setFirstName(firstName2);
        personDto1.setLastName(lastName2);
        personDto1.setAddress(address);
        personDto1.setCity(city);
        personDto1.setPhone(phone);
        personDto1.setEmail(email);
        personDto1.setZip(zip);
    }

    public void settingChangesDtoPerson(PersonDto personDto1, Persons person) {
        personDto1.setFirstName(person.getFirstName());
        personDto1.setLastName(person.getLastName());
        personDto1.setAddress(person.getAddress());
        personDto1.setCity(person.getCity());
        personDto1.setPhone(person.getPhone());
        personDto1.setEmail(person.getEmail());
        personDto1.setZip(person.getZip());
    }

    public List<FirestationByStationNumberDto> getPersonList() {
        logger.info("getPersonList!");
        List<Persons> personsList = personsRepository.findAll();
        List<FirestationByStationNumberDto> firestationByStationNumberDtoArrayList = new ArrayList<>();

        for (Persons person : personsList) {

            FirestationByStationNumberDto firestationByStationNumberDto = new FirestationByStationNumberDto();
            firestationByStationNumberDto.setFirstName(person.getFirstName());
            firestationByStationNumberDto.setLastName(person.getLastName());
            firestationByStationNumberDto.setAddress(person.getAddress());
            firestationByStationNumberDto.setPhone(person.getPhone());
            firestationByStationNumberDtoArrayList.add(firestationByStationNumberDto);

        }
        return firestationByStationNumberDtoArrayList;
    }

    public List<FirestationByStationNumberDto> getPersonsByAddress(String address) {

        List<FirestationByStationNumberDto> persons = getPersonList();
        List<FirestationByStationNumberDto> personsByAddressList = new ArrayList<>();

        for (FirestationByStationNumberDto person : persons) {
            if (person.getAddress().contentEquals(address)) {

                personsByAddressList.add(person);
            }
        }
        return personsByAddressList;
    }

    public List<PersonInfoByNameDto> getPersonByName(String firstName, String lastName) {

        List<Persons> personsList = personsRepository.findAll();
        List<PersonInfoByNameDto> personInfoByNameDtoList = new ArrayList<>();

        for (Persons persons : personsList) {
            if (firstName.equals(persons.getFirstName()) && lastName.equals(persons.getLastName())) {
                PersonInfoByNameDto personInfoByNameDto = new PersonInfoByNameDto();
                personInfoByNameDto.setFirstName(persons.getFirstName());
                personInfoByNameDto.setLastName(persons.getLastName());
                personInfoByNameDto.setAddress(persons.getAddress());
                personInfoByNameDto.setEmail(persons.getEmail());
                personInfoByNameDtoList.add(personInfoByNameDto);
            }
        }
        return personInfoByNameDtoList;
    }

    public PersonDto findPersonByFirstNameAndLastName(String firstName, String lastName) {
        logger.info("checking to find person with the same firstName and lastName ...!");
        List<Persons> personsList = personsRepository.findAll();
        PersonDto personDto = new PersonDto();
        for (Persons persons : personsList) {
            if (persons.getFirstName().equals(firstName) && persons.getLastName().equals(lastName)) {
                settingChangesDtoPerson(personDto,persons);
            }
        }
        return personDto;
    }

    public Persons findPerson(String firstName, String lastName) {
        logger.info("checking to find person with the same firstName and lastName ...!");
        List<Persons> personsList = personsRepository.findAll();

        for (Persons persons : personsList) {
            if (persons.getFirstName().equals(firstName) && persons.getLastName().equals(lastName)) {
                return persons;
            }
        }
        return null;
    }

    public List<PhoneListDto> getPhoneListByAddress(String address) {
        List<FirestationByStationNumberDto> personsList = getPersonsByAddress(address);
        List<PhoneListDto> phoneListDtoList = new ArrayList<>();
        for (FirestationByStationNumberDto person : personsList) {
            if (person.getAddress().equals(address)) {
                PhoneListDto phoneListDto = new PhoneListDto();
                phoneListDto.setPhone(person.getPhone());
                phoneListDtoList.add(phoneListDto);
            }
        }
        return phoneListDtoList;
    }

    public List<CommunityEmailByCityDto> emailListByCity(String city) {

        List<Persons> personsList = personsRepository.findAll();
        List<CommunityEmailByCityDto> communityEmailByCityDtoList = new ArrayList<>();

        for (Persons persons : personsList) {
            if (persons.getCity().contentEquals(city)) {
                CommunityEmailByCityDto communityEmailByCityDto = new CommunityEmailByCityDto("luc@Test.com");
                communityEmailByCityDto.setEmail(persons.getEmail());
                communityEmailByCityDtoList.add(communityEmailByCityDto);
            }
        }
        return communityEmailByCityDtoList;
    }

    public FirestationByStationNumberParentDto getFirestationByStationNumberDto(String station, FireStationsService fireStationsService, MedicalRecordsService medicalRecordsService) throws ParseException {
        List<FirestationsDto> fireStationsByNumber = fireStationsService.getFirestationByStationNumber(station);
        List<FirestationByStationNumberDto> personList = getPersonList();
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
                            int agePers = medicalRecordsService.ageGenerator(persAge);

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

    public ChildAlertListAndFamilyDto getChildAlertListAndFamilyDto(String address, List<FirestationByStationNumberDto> personsByAddressList, MedicalRecordsService medicalRecordsService) throws ParseException {
        List<MedicalRecordsDto> medicalRecordsDtoList = medicalRecordsService.getMedicalRecordsList();
        List<ChildAlertByAddressDto> listOfChildByAddress = new ArrayList<>();
        List<ParentListByAdressDto> listOfParentByAddress = new ArrayList<>();

        for (FirestationByStationNumberDto person : personsByAddressList) {
            for (MedicalRecordsDto pers : medicalRecordsDtoList) {

                if (person.getFirstName().equals(pers.getFirstName()) && person.getLastName().equals(pers.getLastName())) {
                    int age = medicalRecordsService.ageCalculator(pers);
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

    public List<PhoneListDto> getPhoneListDtos(List<FirestationsDto> fireStationsList, List<FirestationByStationNumberDto> listOfPersons) {
        List<PhoneListDto> listOfPhoneDto = new ArrayList<>();

        for (FirestationsDto firestationsDto : fireStationsList) {
            for (FirestationByStationNumberDto person : listOfPersons) {

                if (firestationsDto.getAddress().equals(person.getAddress())) {
                    PhoneListDto phoneListDto = new PhoneListDto();
                    phoneListDto.setPhone(person.getPhone());
                    listOfPhoneDto.add(phoneListDto);
                }
            }
        }
        return listOfPhoneDto;
    }

    public List<FireByAddressDto> getFireByAddressDtos(String address) throws ParseException {
        List<FirestationByStationNumberDto> personsByAddressList = getPersonsByAddress(address);
        List<MedicalRecordsDto> medicalRecordsDtoList = medicalRecordsService.getMedicalRecordsList();
        List<FireByAddressDto> listFireByAddress = new ArrayList<>();

        for (FirestationByStationNumberDto person : personsByAddressList) {
            for (MedicalRecordsDto pers : medicalRecordsDtoList) {

                if ((pers.getLastName().equals(person.getLastName()) && pers.getFirstName().equals(person.getFirstName()))) {
                    int age = medicalRecordsService.ageCalculator(pers);
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


    public List<FloodByListOfStationDto> getFloodByListOfStationDtos(List<String> stations) throws ParseException {
        List<FloodByListOfStationDto> floodByListOfStationDtoList = new ArrayList<>();

        for (String station : stations) {
            List<FirestationsDto> firestationsDtoList = fireStationsService.getFirestationByStationNumber(station);
            List<MedicalRecordsDto> medicalRecordsDtoList = medicalRecordsService.getMedicalRecordsList();

            for (FirestationsDto firestationsDto : firestationsDtoList) {
                List<FirestationByStationNumberDto> firestation = getPersonsByAddress(firestationsDto.getAddress());
                for (FirestationByStationNumberDto fs : firestation) {

                    if (firestationsDto.getAddress().equals(fs.getAddress())) {
                        List<FirestationByStationNumberDto> listPersonByAddress = new ArrayList<>();
                        listPersonByAddress.add(fs);
                        for (FirestationByStationNumberDto person : listPersonByAddress) {
                            for (MedicalRecordsDto medicalRecordsDto : medicalRecordsDtoList) {

                                if (person.getLastName().equals(medicalRecordsDto.getLastName()) && person.getFirstName().equals(medicalRecordsDto.getFirstName())) {
                                    int age = medicalRecordsService.ageCalculator(medicalRecordsDto);
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

    public List<PersonInfoByNameDto> getPersonInfoByNameDtos(String firstName, String lastName) throws ParseException {
        List<PersonInfoByNameDto> personInfoByNameDtoList = getPersonByName(firstName, lastName);
        List<MedicalRecordsDto> medicalRecordsDtoList = medicalRecordsService.getMedicalRecordsList();
        List<PersonInfoByNameDto> listByNameDtoList = new ArrayList<>();

        for (PersonInfoByNameDto persons : personInfoByNameDtoList) {
            for (MedicalRecordsDto pers : medicalRecordsDtoList) {

                if (persons.getFirstName().equals(pers.getFirstName()) && persons.getLastName().equals(pers.getLastName())) {
                    int age = medicalRecordsService.ageCalculator(pers);
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
}
