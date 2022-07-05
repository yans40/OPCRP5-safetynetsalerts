package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")

public class PersonsController {

    @Autowired
    private PersonsService personsService;

    @GetMapping
    public List<Persons> showPersonsList() {

        return personsService.getPersonList();
    }

    @GetMapping ("/address")
    public List<Persons> findPersonsByAddress(@RequestParam String address){

        return personsService.getPersonsByAddress(address);

    }


}
