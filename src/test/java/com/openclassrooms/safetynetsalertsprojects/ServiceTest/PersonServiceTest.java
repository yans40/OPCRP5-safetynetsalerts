package com.openclassrooms.safetynetsalertsprojects.ServiceTest;

import com.openclassrooms.safetynetsalertsprojects.dto.*;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.repository.PersonsRepository;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private FireStationsService fireStationsService;

    @Mock
    private MedicalRecordsService medicalRecordsService;
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
        assertNotEquals("30 rue de spring", personsByAddressList.get(0).getAddress());
    }


    @Test

    public void getPersonByNameTest() {

        List<Persons> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java", "Paris", "0202020202", "BernardK@test.fr", "75000");
        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@test.fr", "77000");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(charlesH);

        when(personsRepository.findAll()).thenReturn(mylistOfFirestationsBystationNumberDto);
        List<PersonInfoByNameDto> personInfoByNameDtoList = personsService.getPersonByName("Bernard", "KARL");

        assertEquals("50 rue de java", personInfoByNameDtoList.get(0).getAddress());
        assertEquals(1, personInfoByNameDtoList.size());
    }

    @Test
    public void getTelephoneListByAddressTest() {
        List<Persons> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java", "Paris", "0202020202", "BernardK@test.fr", "75000");
        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@test.fr", "77000");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(charlesH);

        when(personsRepository.findAll()).thenReturn(mylistOfFirestationsBystationNumberDto);
        List<PhoneListDto> phoneListDtos = personsService.getPhoneListByAddress("30 rue de Spring");

        assertEquals(1, phoneListDtos.size());
        assertEquals("0303030303", phoneListDtos.get(0).getPhone());
    }

    @Test
    public void getPersonlistTest() {
        List<FirestationByStationNumberDto> listToTest = personsService.getPersonList();

        assertEquals(listToTest.size(), personsRepository.findAll().size());
    }

    @Test
    public void emailListTest() {
        List<Persons> mylistOfFirestationsBystationNumberDto = new ArrayList<>();


        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java ", "Paris", "0202020202", "BernardK@test.fr", "75000");
        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@test.fr", "77000");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(charlesH);

        when(personsRepository.findAll()).thenReturn(mylistOfFirestationsBystationNumberDto);
        List<CommunityEmailByCityDto> medicalRecordsDtoList = personsService.emailListByCity("Paris");

        assertEquals(1, medicalRecordsDtoList.size());
        verify(personsRepository, times(1)).findAll();
    }

    @Test
    public void findPersonTest() {

        List<Persons> personsList = new ArrayList<>();

        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java ", "Paris", "0202020202", "BernardK@test.fr", "75000");
        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@tes", "77000");

        personsList.add(alainB);
        personsList.add(bernardK);
        personsList.add(charlesH);

        when(personsRepository.findAll()).thenReturn(personsList);
        Persons person = personsService.findPerson("Charles", "HENRY");
        assertEquals(person, personsList.get(2));
        assertEquals("Charles", person.getFirstName());
        assertEquals("HENRY", person.getLastName());

    }

    @Test
    public void findPersonDtoTest() {

        List<PersonDto> personsListDto = new ArrayList<>();

        PersonDto charlesH = new PersonDto("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@tes", "77000");
        PersonDto alainB = new PersonDto("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        PersonDto bernardK = new PersonDto("Bernard", "KARL", "50 rue de java ", "Paris", "0202020202", "BernardK@test.fr", "75000");

        personsListDto.add(alainB);
        personsListDto.add(bernardK);
        personsListDto.add(charlesH);

        PersonDto personDto = personsService.findPersonByFirstNameAndLastName("Alain", "BOUCHON");
        verify(personsRepository, times(1)).findAll();
    }


    @Test
    public void deletePersonTest() {
        List<Persons> personsList = new ArrayList<>();

        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@tes", "77000");
        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java ", "Paris", "0202020202", "BernardK@test.fr", "75000");

        personsList.add(alainB);
        personsList.add(bernardK);
        personsList.add(charlesH);

        when(personsRepository.findAll()).thenReturn(personsList);
        personsService.deletePerson("Alain", "BOUCHON");
        verify(personsRepository, times(1)).findAll();
        assertEquals("Bernard", personsList.get(1).getFirstName());
    }

    @Test
    public void getIndexPersonsTest() {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("Charles");
        personDto.setLastName("HENRY");

        List<Persons> personsList = new ArrayList<>();

        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@tes", "77000");
        Persons alainB = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons bernardK = new Persons("Bernard", "KARL", "50 rue de java ", "Paris", "0202020202", "BernardK@test.fr", "75000");

        personsList.add(alainB);
        personsList.add(bernardK);
        personsList.add(charlesH);

        when(personsRepository.findAll()).thenReturn(personsList);
        int index = personsService.getIndexOfPersons(personDto);
        assertEquals(2, index);
    }

    @Test
    public void dtoToPersonTest() {
        PersonDto personDto = new PersonDto("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons persons = personsService.dtoToPerson(personDto);

        assertEquals(persons.getFirstName(), personDto.getFirstName());
        assertEquals(persons.getLastName(), personDto.getLastName());
        assertEquals(persons.getAddress(), personDto.getAddress());
        assertEquals(persons.getCity(), personDto.getCity());
        assertEquals(persons.getPhone(), personDto.getPhone());
        assertEquals(persons.getEmail(), personDto.getEmail());
        assertEquals(persons.getZip(), personDto.getZip());
    }

    @Test
    public void settingChangesDtoPerson() {
        PersonDto personDto = new PersonDto();
        Persons charlesH = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@tes", "77000");

        personsService.settingChangesDtoPerson(personDto, charlesH);

        assertEquals(personDto.getFirstName(), charlesH.getFirstName());
        assertEquals(personDto.getCity(), charlesH.getCity());
    }

    @Test
    public void getPhoneListDtosTest() {
        List<FirestationsDto> firestationsDtoList = new ArrayList<>();
        List<FirestationByStationNumberDto> firestationByStationNumberDtoList = new ArrayList<>();

        FirestationsDto firestationsDto1 = new FirestationsDto("30 rue de Spring", "2");
        FirestationsDto firestationsDto2 = new FirestationsDto("5 rue de nanterre", "1");
        FirestationsDto firestationsDto3 = new FirestationsDto("3 rue de paris", "3");

        firestationsDtoList.add(firestationsDto1);
        firestationsDtoList.add(firestationsDto2);
        firestationsDtoList.add(firestationsDto3);

        FirestationByStationNumberDto firestationByStationNumberDto1 = new FirestationByStationNumberDto("Charles", "HENRY", "30 rue de Spring", "2");
        FirestationByStationNumberDto firestationByStationNumberDto2 = new FirestationByStationNumberDto("Chantal", "HENRY", "30 rue de Spring", "2");
        FirestationByStationNumberDto firestationByStationNumberDto3 = new FirestationByStationNumberDto("Gerard", "BONNET", "10 avenue de nanterre", "3");

        firestationByStationNumberDtoList.add(firestationByStationNumberDto1);
        firestationByStationNumberDtoList.add(firestationByStationNumberDto2);
        firestationByStationNumberDtoList.add(firestationByStationNumberDto3);

        List<PhoneListDto> phoneListDtoList = personsService.getPhoneListDtos(firestationsDtoList, firestationByStationNumberDtoList);

        assertEquals(2, phoneListDtoList.size());
    }

    @Test
    public void settingDtoChangesTest() {
        PersonDto personDto = new PersonDto("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@tes", "77000");

        personsService.settingPersonDtoChanges(personDto, "Charles", "HENRY", "30 rue de spring", "paris", "01020304", "springC@tes", "75000");

        assertEquals("paris", personDto.getCity());
    }

    @Test
    public void updatePersonTest() {
        PersonDto personDto = new PersonDto("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@tes", "77000");
        PersonDto personDto1 = new PersonDto("Charles", "HENRY", "30 rue de spring", "paris", "01020304", "springC@tes", "75000");
        personsService.updatePerson("Charles", "HENRY", personDto1);

        assertEquals(personDto.getCity(), personDto1.getCity());
    }

    @Test
    public void getFirestationByStationNumberDtoTest() throws ParseException {
        List<FirestationsDto> firestationsDtoList = new ArrayList<>();
        FirestationsDto firestationsDto = new FirestationsDto("1 rue de la vie", "2");
        FirestationsDto firestationsDto1 = new FirestationsDto("2 rue de la paix", "2");

        firestationsDtoList.add(firestationsDto);
        firestationsDtoList.add(firestationsDto1);

        List<FirestationByStationNumberDto> firestationByStationNumberDtoList = new ArrayList<>();
        FirestationByStationNumberDto firestationByStationNumberDto = new FirestationByStationNumberDto("Jean", "ROBERT", "1 rue de la vie", "01010101");
        FirestationByStationNumberDto firestationByStationNumberDto1 = new FirestationByStationNumberDto("louis", "GERARD", "1 rue de la paix", "020220220");

        firestationByStationNumberDtoList.add(firestationByStationNumberDto);
        firestationByStationNumberDtoList.add(firestationByStationNumberDto1);

        List<MedicalRecordsDto> medicalRecordsDtoList = new ArrayList<>();

        MedicalRecordsDto medicalRecordsDto = new MedicalRecordsDto("Jean", "ROBERT", "12/01/1980", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));
        MedicalRecordsDto medicalRecordsDto1 = new MedicalRecordsDto("lea", "BENOIT", "23/01/1981", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        medicalRecordsDtoList.add(medicalRecordsDto);
        medicalRecordsDtoList.add(medicalRecordsDto1);

        when(fireStationsService.getFirestationByStationNumber("2")).thenReturn(firestationsDtoList);
        when(medicalRecordsService.getMedicalRecordsList()).thenReturn(medicalRecordsDtoList);
        personsService.getFirestationByStationNumberDto("2", fireStationsService, medicalRecordsService);

        verify(fireStationsService, times(1)).getFirestationByStationNumber("2");
        verify(medicalRecordsService, times(1)).getMedicalRecordsList();
    }

    @Test
    public void getFireByAddressDtosTest() throws ParseException {
        List<FirestationByStationNumberDto> firestationByStationNumberDtoList = new ArrayList<>();
        FirestationByStationNumberDto firestationByStationNumberDto = new FirestationByStationNumberDto("Jean", "ROBERT", "1 rue de la vie", "01010101");
        FirestationByStationNumberDto firestationByStationNumberDto1 = new FirestationByStationNumberDto("louis", "GERARD", "1 rue de la paix", "020220220");

        firestationByStationNumberDtoList.add(firestationByStationNumberDto);
        firestationByStationNumberDtoList.add(firestationByStationNumberDto1);

        List<MedicalRecordsDto> medicalRecordsDtoList = new ArrayList<>();

        MedicalRecordsDto medicalRecordsDto = new MedicalRecordsDto("Jean", "ROBERT", "12/01/1980", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));
        MedicalRecordsDto medicalRecordsDto1 = new MedicalRecordsDto("lea", "BENOIT", "23/01/1981", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        medicalRecordsDtoList.add(medicalRecordsDto);
        medicalRecordsDtoList.add(medicalRecordsDto1);

        when(medicalRecordsService.getMedicalRecordsList()).thenReturn(medicalRecordsDtoList);
        List<FireByAddressDto> list = personsService.getFireByAddressDtos("1 rue de la vie");

        verify(medicalRecordsService, times(1)).getMedicalRecordsList();
    }

    @Test
    public void getFloodByListOfStationsDtosTest() throws ParseException {
        List<FirestationsDto> firestationsDtoList = new ArrayList<>();
        List<FirestationByStationNumberDto> firestationByStationNumberDtoList = new ArrayList<>();

        FirestationsDto firestationsDto1 = new FirestationsDto("30 rue de Spring", "2");
        FirestationsDto firestationsDto2 = new FirestationsDto("5 rue de nanterre", "1");
        FirestationsDto firestationsDto3 = new FirestationsDto("3 rue de paris", "3");

        firestationsDtoList.add(firestationsDto1);
        firestationsDtoList.add(firestationsDto2);
        firestationsDtoList.add(firestationsDto3);

        List<MedicalRecordsDto> medicalRecordsDtoList = new ArrayList<>();

        MedicalRecordsDto medicalRecordsDto = new MedicalRecordsDto("Jean", "ROBERT", "12/01/1980", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));
        MedicalRecordsDto medicalRecordsDto1 = new MedicalRecordsDto("lea", "BENOIT", "23/01/1981", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        medicalRecordsDtoList.add(medicalRecordsDto);
        medicalRecordsDtoList.add(medicalRecordsDto1);

        when(medicalRecordsService.getMedicalRecordsList()).thenReturn(medicalRecordsDtoList);
        personsService.getFloodByListOfStationDtos(Collections.singletonList("2"));

        verify(fireStationsService, times(1)).getFirestationByStationNumber("2");
    }

    @Test
    public void getPersonInfoByNameDtosTest() throws ParseException {
        List<PersonInfoByNameDto> personInfoByNameDtoList = new ArrayList<>();
        PersonInfoByNameDto personInfoByNameDto = new PersonInfoByNameDto("Jean", "ROBERT", "1 rue de la paix", 42, "jean@mail.com", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));
        personInfoByNameDtoList.add(personInfoByNameDto);

        List<MedicalRecordsDto> medicalRecordsDtoList = new ArrayList<>();

        MedicalRecordsDto medicalRecordsDto = new MedicalRecordsDto("Jean", "ROBERT", "12/01/1980", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));
        MedicalRecordsDto medicalRecordsDto1 = new MedicalRecordsDto("lea", "BENOIT", "23/01/1981", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        medicalRecordsDtoList.add(medicalRecordsDto);
        medicalRecordsDtoList.add(medicalRecordsDto1);

        when(medicalRecordsService.getMedicalRecordsList()).thenReturn(medicalRecordsDtoList);

        List<PersonInfoByNameDto> list = personsService.getPersonInfoByNameDtos("Jean", "ROBERT");
        verify(medicalRecordsService, times(1)).getMedicalRecordsList();
    }
}
