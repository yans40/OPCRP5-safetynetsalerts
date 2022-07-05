package com.openclassrooms.safetynetsalertsprojects.service;

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

    public List<Persons> getPersonList() {

        return personsRepository.findAll();
    }

    public List<Persons> getPersonsByAddress(String address) {

        List<Persons> persons = personsRepository.findAll();
        List<Persons> personsByAddressList = new ArrayList<>();

        for (Persons person : persons) {
            if (person.getAddress().contentEquals(address)) {

                personsByAddressList.add(person);
            }
        }
        return personsByAddressList;
    }

}
