package com.openclassrooms.safetynetsalertsprojects.repository;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonsRepository {
    @Autowired
    private DataSource dataSource;


    public List<Persons> findAll() {
        return dataSource.getPersons();
    }


    public void save(Persons persons) {
     List<Persons> personsList = findAll();
     personsList.add(persons);
    }

}
