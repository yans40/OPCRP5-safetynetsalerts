package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.dto.CommunityEmailByCityDto;
import com.openclassrooms.safetynetsalertsprojects.dto.FirestationByStationNumberDto;
import com.openclassrooms.safetynetsalertsprojects.dto.PersonInfoByNameDto;
import com.openclassrooms.safetynetsalertsprojects.dto.PhoneListDto;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonsService {
    @Autowired
    private PersonsRepository personsRepository;


    public List<FirestationByStationNumberDto> getPersonList() {

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
                CommunityEmailByCityDto communityEmailByCityDto = new CommunityEmailByCityDto();
                communityEmailByCityDto.setEmail(persons.getEmail());
                communityEmailByCityDtoList.add(communityEmailByCityDto);
            }
        }
        return communityEmailByCityDtoList;
    }


}
