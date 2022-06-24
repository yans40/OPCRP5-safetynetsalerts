package com.openclassrooms.safetynetsalertsprojects.repository;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonsRepository {
    @Autowired
    DataSource dataSource;

    public List<Persons> findAll() {
        return dataSource.getPersons();
    }

}
