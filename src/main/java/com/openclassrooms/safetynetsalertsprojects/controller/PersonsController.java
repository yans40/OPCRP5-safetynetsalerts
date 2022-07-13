package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.PersonsDto;
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
    public List<PersonsDto> showPersonsList() {

        return personsService.getPersonList();
    }

    @GetMapping ("/address")
    public List<PersonsDto> findPersonsByAddress(@RequestParam String address){

        return personsService.getPersonsByAddress(address);

    }


}
