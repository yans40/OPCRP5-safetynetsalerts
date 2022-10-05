package com.openclassrooms.safetynetsalertsprojects.ControlTest;


import com.openclassrooms.safetynetsalertsprojects.dto.FirestationsDto;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class FirestationsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FireStationsService fireStationsService;

    @Test
    public void shouldReturnListOfFirestation() throws Exception {

        Map<String,String> firestationsMap = new HashMap<>();

        firestationsMap.put("50 rue de java ","2");
        firestationsMap.put("20 rue du lac","1");

        when(fireStationsService.getFireStationsHashMap()).thenReturn(firestationsMap);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/firestations"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnListOfFirestationDto() throws Exception {

        List<FirestationsDto> firestationsDtoList = new ArrayList<>();
        FirestationsDto firestationsDto = new FirestationsDto("50 rue de java ","2");
        FirestationsDto firestationsDto1=new FirestationsDto("20 rue du lac","1");

        firestationsDtoList.add(firestationsDto);
        firestationsDtoList.add(firestationsDto1);

        when(fireStationsService.getFireStationsList()).thenReturn(firestationsDtoList);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/getfirestations"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnListOfStationWhereNumberIs() throws Exception {

        List<FirestationsDto> fireStationsList = new ArrayList<>();

        FirestationsDto fireStations1 = new FirestationsDto("1 rue d'alsace","1");
        FirestationsDto fireStations2 = new FirestationsDto("4 rue de la rive","2");
        FirestationsDto fireStations3 = new FirestationsDto("25 rue de la vie","1");

        fireStationsList.add(fireStations1);
        fireStationsList.add(fireStations2);
        fireStationsList.add(fireStations3);

        when(fireStationsService.getFirestationByStationNumber("2")).thenReturn(fireStationsList);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/stationNumber?station=station"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shoulDeleteAFirestation() throws Exception {

     this.mockMvc.perform(MockMvcRequestBuilders
             .delete("/firestations/delete/{address}","25 rue de la vie")
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk());
    }
    @Test
    public void shouldCreateAFirestation() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"address\": \"2 rue de la paix \",\n" +
                                "    \"station\": \"2\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldUpdateAFirestation() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/firestations/update/{address}","3 rue de la plage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"address\": \"2 rue de la paix \",\n" +
                                "    \"station\": \"2\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }
}


