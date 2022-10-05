package com.openclassrooms.safetynetsalertsprojects.ControlTest;

import com.openclassrooms.safetynetsalertsprojects.dto.*;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
    private PersonsService personsService;

    @MockBean

    private FireStationsService fireStationsService;
    @MockBean
    private MedicalRecordsService medicalRecordsService;


    @Test
    void shouldReturnListOfPersons() throws Exception {

        List<FirestationByStationNumberDto> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        FirestationByStationNumberDto alainB = new FirestationByStationNumberDto("Alain", "BOUCHON", "01 rue du Mock", "0101010101");
        FirestationByStationNumberDto bernardK = new FirestationByStationNumberDto("Bernard", "KARL", "50 rue de java", "0202020202");
        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);

        when(personsService.getPersonList()).thenReturn(mylistOfFirestationsBystationNumberDto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/persons"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Alain")))
                .andExpect(jsonPath("$[1].lastName", is("KARL")))
                .andExpect(jsonPath("$[1].address", is("50 rue de java")));
    }

    @Test
    void shouldReturnByStationNumberLisTest() throws Exception {

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
        when(personsService.getFirestationByStationNumberDto("station", fireStationsService, medicalRecordsService)).thenReturn(listToTest);


        mockMvc.perform(MockMvcRequestBuilders
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

        List<FirestationByStationNumberDto> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        FirestationByStationNumberDto alainB = new FirestationByStationNumberDto("Alain", "BOUCHON", "01 rue du Mock", "0101010101");
        FirestationByStationNumberDto aliceM = new FirestationByStationNumberDto("Alice", "MULLER", "62 rue du voyage", "0101010101");
        FirestationByStationNumberDto bernardK = new FirestationByStationNumberDto("Bernard", "KARL", "50 rue de java", "0202020202");
        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(aliceM);

        when(personsService.getChildAlertListAndFamilyDto("address", mylistOfFirestationsBystationNumberDto, medicalRecordsService)).thenReturn(listToTest);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/childAlert?address=address"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnPhoneListByFireStationNumber() throws Exception {

        List<FirestationsDto> firestationsDtoList = new ArrayList<>();
        FirestationsDto firestationsDto1 = new FirestationsDto("50 rue de java", "2");
        FirestationsDto firestationsDto2 = new FirestationsDto("01 rue du Mock", "3");


        firestationsDtoList.add(firestationsDto1);
        firestationsDtoList.add(firestationsDto2);

        List<FirestationByStationNumberDto> mylistOfFirestationsBystationNumberDto = new ArrayList<>();
        FirestationByStationNumberDto alainB = new FirestationByStationNumberDto("Alain", "BOUCHON", "01 rue du Mock", "0101010101");
        FirestationByStationNumberDto aliceM = new FirestationByStationNumberDto("Alice", "MULLER", "62 rue du voyage", "0101010101");
        FirestationByStationNumberDto bernardK = new FirestationByStationNumberDto("Bernard", "KARL", "50 rue de java", "0202020202");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(aliceM);

       List <PhoneListDto> phoneListDto = new ArrayList<>();

        PhoneListDto phoneListDto1=new PhoneListDto("0101010101");
        PhoneListDto phoneListDto2=new PhoneListDto("0202020202");

        phoneListDto.add(phoneListDto1);
        phoneListDto.add(phoneListDto2);

        when(personsService.getPhoneListDtos(firestationsDtoList,mylistOfFirestationsBystationNumberDto)).thenReturn(phoneListDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/phoneAlert?firestation=firestation_number"))
                .andDo(print())
                .andExpect(status().isOk());
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

        when(personsService.getFireByAddressDtos("address")).thenReturn(listOfTest);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fire?address=address"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void shouldReturnListOfPersonsAndMedicalRecordsByAddress() throws Exception {

        List<FloodByListOfStationDto> listOfTest = new ArrayList<>();

        FloodByListOfStationDto pers1 = new FloodByListOfStationDto("BOUCHER", "01010101", 32, (List.of("ibupurin:200mg", "hydrapermazol:400mg")), (List.of("peanut", "shellfish", "aznol")));
        FloodByListOfStationDto pers2 = new FloodByListOfStationDto("MOREAU", "02020202", 15, (List.of("ibupurin:200mg", "hydrapermazol:400mg")), (List.of()));
        FloodByListOfStationDto pers3 = new FloodByListOfStationDto("ALLAN", "03030033", 26, (List.of("noxidian:100mg", "pharmacol:2500mg")), (List.of("shellfish")));
        FloodByListOfStationDto pers4 = new FloodByListOfStationDto("DUMAS", "0404040404", 65, (List.of("tradoxidine:400mg")), (List.of("xilliathal")));

        listOfTest.add(pers1);
        listOfTest.add(pers2);
        listOfTest.add(pers3);
        listOfTest.add(pers4);

        when(personsService.getFloodByListOfStationDtos(Collections.singletonList("stations"))).thenReturn(listOfTest);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/flood/stations?stations=stations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void shouldReturnPersonsInfoByName() throws Exception {
        List<PersonInfoByNameDto> listOfTest = new ArrayList<>();

        PersonInfoByNameDto pers1 = new PersonInfoByNameDto("bernard", "BOUCHER", "02 rue de Paris", 32, "berna@test.com", (List.of("ibupurin:200mg", "hydrapermazol:400mg")), (List.of("peanut", "shellfish", "aznol")));
        PersonInfoByNameDto pers2 = new PersonInfoByNameDto("alex", "DUPONT", "02 rue de Nice", 45, "alexandre@test.com", (List.of("doliprane:200mg", "mazol:200mg")), (List.of("peanut")));

        listOfTest.add(pers1);
        listOfTest.add(pers2);

        when(personsService.getPersonInfoByNameDtos("firstName", "lastName")).thenReturn(listOfTest);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/personInfo?firstName=firstName&lastName=lastName"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }


    @Test
    public void shouldReturnEmailOfPersonsinCity() throws Exception {

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

        when(personsService.emailListByCity("city")).thenReturn(emailList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/communityEmail?city=city"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void shoulDeleteAPersons() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/persons/delete/{firstName}&{lastName}", "leo", "messi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldCreateAPerson() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\": \"leo\",\n" +
                        "    \"lastName\": \"messi\",\n" +
                        "    \"address\": \"10 avenue des princes\",\n" +
                        "    \"city\": \"Paris\",\n" +
                        "    \"zip\": \"91280\",\n" +
                        "    \"phone\": \"841-874-2012\",\n" +
                        "    \"email\": \"messi@email.com\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateAPerson() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/persons/update/{firstName}&{lastName}", "leo", "messi")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\": \"leo\",\n" +
                        "    \"lastName\": \"messi\",\n" +
                        "    \"address\": \"10 avenue des princes\",\n" +
                        "    \"city\": \"Paris\",\n" +
                        "    \"zip\": \"91280\",\n" +
                        "    \"phone\": \"841-874-2012\",\n" +
                        "    \"email\": \"messi@email.com\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }
}

































