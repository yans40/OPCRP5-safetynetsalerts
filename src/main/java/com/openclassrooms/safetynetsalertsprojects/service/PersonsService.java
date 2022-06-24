package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsService {
    @Autowired
    PersonsRepository personsRepository;

    public List<Persons> getPersonList(){

        return personsRepository.findAll();
    }

}
