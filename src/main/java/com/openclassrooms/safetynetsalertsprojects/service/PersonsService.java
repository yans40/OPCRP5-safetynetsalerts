package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.dto.CommunityEmailByCityDto;
import com.openclassrooms.safetynetsalertsprojects.dto.FirestationByStationNumberDto;
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


    public List<CommunityEmailByCityDto> emailListByCity(String city) {

        List<Persons> personsList = personsRepository.findAll();
        List<CommunityEmailByCityDto> communityEmailByCityDtoList = new ArrayList<>();

        for (Persons persons : personsList) {
            if (persons.getCity().contentEquals(city)) {
             CommunityEmailByCityDto   communityEmailByCityDto = new CommunityEmailByCityDto();
               communityEmailByCityDto.setEmail(persons.getEmail());
                communityEmailByCityDtoList.add(communityEmailByCityDto);
            }
        }
        return communityEmailByCityDtoList;
    }
}
