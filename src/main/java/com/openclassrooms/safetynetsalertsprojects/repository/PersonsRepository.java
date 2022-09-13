package com.openclassrooms.safetynetsalertsprojects.repository;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonsRepository {

    private static final Logger logger = LogManager.getLogger(PersonsRepository.class);
    @Autowired
    private DataSource dataSource;


    public List<Persons> findAll() {
        return dataSource.getPersons();
    }


    public void save(Persons persons) {
        logger.info("save ok !");
        List<Persons> personsList = findAll();
        personsList.add(persons);
    }

    public void update(int index, Persons persons) {
        logger.info("update ok !");
        List<Persons> list = findAll();
        list.set(index, persons);
    }

    public void delete(Persons persons) {
        logger.info("delete with index !");
        List<Persons> personsList = findAll();
        personsList.remove(persons);
        logger.info("delete successful!");
    }
}
