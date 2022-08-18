package com.openclassrooms.safetynetsalertsprojects.ControllerTest;

import com.openclassrooms.safetynetsalertsprojects.controller.PersonsController;
import com.openclassrooms.safetynetsalertsprojects.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonsController personsController;


    @Test
    void shouldReturnListOfPersons() throws Exception {

        List<FirestationByStationNumberDto> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        FirestationByStationNumberDto alainB = new FirestationByStationNumberDto("Alain", "BOUCHON", "01 rue du Mock", "0101010101");
        FirestationByStationNumberDto bernardK = new FirestationByStationNumberDto("Bernard", "KARL", "50 rue de java", "0202020202");
        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);

        when(personsController.showPersonsList()).thenReturn(mylistOfFirestationsBystationNumberDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/persons"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Alain")))
                .andExpect(jsonPath("$[1].lastName", is("KARL")));
    }

    @Test
    void shouldReturnByStationNumberList() throws Exception {

        List<FirestationByStationNumberDto> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        FirestationByStationNumberDto alainB = new FirestationByStationNumberDto("Alain", "BOUCHON", "01 rue du Mock", "0101010101");
        FirestationByStationNumberDto aliceM = new FirestationByStationNumberDto("Alice", "MULLER", "62 rue du voyage", "0101010101");
        FirestationByStationNumberDto bernardK = new FirestationByStationNumberDto("Bernard", "KARL", "50 rue de java", "0202020202");
        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(aliceM);


        FirestationByStationNumberParentDto listToTest = new FirestationByStationNumberParentDto();
        listToTest.setPersonsInfo(mylistOfFirestationsBystationNumberDto);
        listToTest.setNbAdult(1);
        listToTest.setNbChild(1);


        when(personsController.personInPotentialRisk("station")).thenReturn(listToTest);


        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/firestation?station=station"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$..[0]..firstName", is(List.of("Alain", "Bernard", "Alice"))));
    }

    @Test
    void shouldReturnNameAndAgeOfChildByAddress() throws Exception {

        ChildAlertListAndFamilyDto listToTest = new ChildAlertListAndFamilyDto();

        List<ChildAlertByAddressDto> childList = new ArrayList<>();
        ChildAlertByAddressDto julieB = new ChildAlertByAddressDto("julie", "BARBIE", 15);
        ChildAlertByAddressDto zoeD = new ChildAlertByAddressDto("zoe", "DUPONT", 10);
        childList.add(julieB);
        childList.add(zoeD);
        List<ParentListByAdressDto> parentList = new ArrayList<>();
        ParentListByAdressDto bernardB = new ParentListByAdressDto("bernard", "BARBIE");
        ParentListByAdressDto edithD = new ParentListByAdressDto("edith", "DUPONT");
        parentList.add(bernardB);
        parentList.add(edithD);


        listToTest.setParentListByAdressDtoList(parentList);
        listToTest.setChildAlertByAdressDtoList(childList);

        when(personsController.findChildAndFamily("address")).thenReturn(listToTest);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/childAlert?address=address"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..[0].firstName", is(List.of("julie", "bernard"))));


    }

    @Test
    void shouldReturnPhoneListByFireStationNumber() throws Exception {

        List<PhoneListDto> listOfTest = new ArrayList<>();

        PhoneListDto tel1 = new PhoneListDto("0101010101");
        PhoneListDto tel2 = new PhoneListDto("0202020202");
        listOfTest.add(tel1);
        listOfTest.add(tel2);

        when(personsController.phoneAlertListByStation("firestation_number")).thenReturn(listOfTest);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/phoneAlert?firestation=firestation_number"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void shouldReturnPersonsAndFirestationByAddress() throws Exception {

        List<FireByAddressDto> listOfTest = new ArrayList<>();

        FireByAddressDto pers1 = new FireByAddressDto("bernard", "BOUCHER", "01010101", 32, (List.of("ibupurin:200mg", "hydrapermazol:400mg")), (List.of("peanut", "shellfish", "aznol")));
        FireByAddressDto pers2 = new FireByAddressDto("xavier", "MOREAU", "02020202", 15, (List.of("ibupurin:200mg", "hydrapermazol:400mg")), (List.of()));
        FireByAddressDto pers3 = new FireByAddressDto("juliette", "ALLAN", "03030033", 26, (List.of("noxidian:100mg", "pharmacol:2500mg")), (List.of("shellfish")));
        FireByAddressDto pers4 = new FireByAddressDto("alexandre", "DUMAS", "0404040404", 65, (List.of("tradoxidine:400mg")), (List.of("xilliathal")));

        listOfTest.add(pers1);
        listOfTest.add(pers2);
        listOfTest.add(pers3);
        listOfTest.add(pers4);

        when(personsController.fireByAddressList("address")).thenReturn(listOfTest);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/fire?address=address"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());

    }

    @Test
    void shouldReturnListOfPersonsAndMedicalRecordsByAddress() throws Exception {

        List<FloodByListOfStationDto> listOfTest = new ArrayList<>();

        FloodByListOfStationDto pers1 = new FloodByListOfStationDto("BOUCHER", "01010101", 32, (List.of("ibupurin:200mg", "hydrapermazol:400mg")), (List.of("peanut", "shellfish", "aznol")));
        FloodByListOfStationDto pers2 = new FloodByListOfStationDto("MOREAU", "02020202", 15, (List.of("ibupurin:200mg", "hydrapermazol:400mg")), (List.of()));
        FloodByListOfStationDto pers3 = new FloodByListOfStationDto("ALLAN", "03030033", 26, (List.of("noxidian:100mg", "pharmacol:2500mg")), (List.of("shellfish")));
        FloodByListOfStationDto pers4 = new FloodByListOfStationDto("DUMAS", "0404040404", 65, (List.of("tradoxidine:400mg")), (List.of("xilliathal")));

        listOfTest.add(pers1);
        listOfTest.add(pers2);
        listOfTest.add(pers3);
        listOfTest.add(pers4);

        when(personsController.floodListByStation(Collections.singletonList("stations"))).thenReturn(listOfTest);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/flood/stations?stations=stations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void shouldReturnPersonsInfoByName() throws Exception {
        List<PersonInfoByNameDto> listOfTest = new ArrayList<>();

        PersonInfoByNameDto pers1 = new PersonInfoByNameDto("bernard", "BOUCHER", "02 rue de Paris", 32, "berna@test.com", (List.of("ibupurin:200mg", "hydrapermazol:400mg")), (List.of("peanut", "shellfish", "aznol")));
        PersonInfoByNameDto pers2 = new PersonInfoByNameDto("alex", "DUPONT", "02 rue de Nice", 45, "alexandre@test.com", (List.of("doliprane:200mg", "mazol:200mg")), (List.of("peanut")));

        listOfTest.add(pers1);
        listOfTest.add(pers2);

        when(personsController.personsListByFirstNameAndLastName("firstName", "lastName")).thenReturn(listOfTest);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/personInfo?firstName=firstName&lastName=lastName"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }


    @Test
    void shouldReturnEmailOfPersonsinCity() throws Exception {

        List<CommunityEmailByCityDto> emailList = new ArrayList<>();

        CommunityEmailByCityDto a = new CommunityEmailByCityDto("luc@Test.com");
        CommunityEmailByCityDto b = new CommunityEmailByCityDto("pierre@Test.com");
        CommunityEmailByCityDto c = new CommunityEmailByCityDto("julie@Test.com");
        CommunityEmailByCityDto d = new CommunityEmailByCityDto("zoe@Test.com");
        CommunityEmailByCityDto e = new CommunityEmailByCityDto("jean@Test.com");


        emailList.add(a);
        emailList.add(b);
        emailList.add(c);
        emailList.add(d);
        emailList.add(e);

        when(personsController.emailByCity("city")).thenReturn(emailList);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/communityEmail?city=city"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

}

































