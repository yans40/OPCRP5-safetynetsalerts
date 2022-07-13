package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.dto.PersonsDto;
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


    public List<PersonsDto> getPersonList() {

        List<Persons> personsList = personsRepository.findAll();
        List<PersonsDto> personsDtoArrayList = new ArrayList<>();
        for (Persons person : personsList) {
            PersonsDto personsDto = new PersonsDto();
            personsDto.setFirstName(person.getFirstName());
            personsDto.setLastName(person.getLastName());
            personsDto.setAddress(person.getAddress());
            personsDto.setPhone(person.getPhone());
            personsDtoArrayList.add(personsDto);
        }
        return personsDtoArrayList;
    }

    public List<PersonsDto> getPersonsByAddress(String address) {

        List<PersonsDto> persons = getPersonList();
        List<PersonsDto> personsByAddressList = new ArrayList<>();

        for (PersonsDto person : persons) {
            if (person.getAddress().contentEquals(address)) {

                personsByAddressList.add(person);
            }
        }
        return personsByAddressList;
    }


    public List<Persons> sameFamilyList(String lastname, String Address) {

        List<Persons> personsList = personsRepository.findAll();
        List<Persons> familyList = new ArrayList<>();

        for (Persons person : personsList) {
            if ((person.getLastName() + person.getAddress()).contentEquals(lastname + Address)) {

                familyList.add(person);
            }
        }
        return familyList;
    }

}
