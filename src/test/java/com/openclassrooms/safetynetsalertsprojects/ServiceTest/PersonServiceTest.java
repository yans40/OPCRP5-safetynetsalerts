package com.openclassrooms.safetynetsalertsprojects.ServiceTest;

import com.openclassrooms.safetynetsalertsprojects.dto.FirestationByStationNumberDto;
import com.openclassrooms.safetynetsalertsprojects.dto.PersonInfoByNameDto;
import com.openclassrooms.safetynetsalertsprojects.dto.PhoneListDto;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.repository.PersonsRepository;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonsRepository personsRepository;

    @InjectMocks
    private PersonsService personsService;


    @Test

    public void getPersonListTest() {

        List<Persons> mylistOfFirestationsBystationNumberDto = new ArrayList<>();


        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java ", "Paris", "0202020202", "BernardK@test.fr", "75000");
        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@test.fr", "77000");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(charlesH);


        when(personsRepository.findAll()).thenReturn(mylistOfFirestationsBystationNumberDto);
        List<FirestationByStationNumberDto> medicalRecordsDtoList = personsService.getPersonList();

        assertEquals(3, medicalRecordsDtoList.size());
        assertNotNull(bernardK.getAddress());
        verify(personsRepository, times(1)).findAll();

    }

    @Test
    public void getpersonByAddress() {

        List<Persons> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java ", "Paris", "0202020202", "BernardK@test.fr", "75000");
        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@test.fr", "77000");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(charlesH);

        when(personsRepository.findAll()).thenReturn(mylistOfFirestationsBystationNumberDto);
        List<FirestationByStationNumberDto> personsByAddressList = personsService.getPersonsByAddress("01 rue du Mock");

        assertEquals("Alain", personsByAddressList.get(0).getFirstName());
        assertEquals("0101010101", personsByAddressList.get(0).getPhone());
        assertNotEquals("30 rue de spring",personsByAddressList.get(0).getAddress());

    }


    @Test

    public void getPersonByNameTest(){

        List<Persons> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java", "Paris", "0202020202", "BernardK@test.fr", "75000");
        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@test.fr", "77000");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(charlesH);

        when(personsRepository.findAll()).thenReturn(mylistOfFirestationsBystationNumberDto);
        List<PersonInfoByNameDto> personInfoByNameDtoList = personsService.getPersonByName("Bernard","KARL");

        assertEquals("50 rue de java",personInfoByNameDtoList.get(0).getAddress());
        assertEquals(1,personInfoByNameDtoList.size());
    }

    @Test
    public void getTelephoneListByAddressTest(){
        List<Persons> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java", "Paris", "0202020202", "BernardK@test.fr", "75000");
        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@test.fr", "77000");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(charlesH);

        when(personsRepository.findAll()).thenReturn(mylistOfFirestationsBystationNumberDto);
        List<PhoneListDto> phoneListDtos = personsService.getPhoneListByAddress("30 rue de Spring");

        assertEquals(1,phoneListDtos.size());
        assertEquals("0303030303",phoneListDtos.get(0).getPhone());


    }
}
