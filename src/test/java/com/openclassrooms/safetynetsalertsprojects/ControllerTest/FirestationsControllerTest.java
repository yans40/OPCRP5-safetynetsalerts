package com.openclassrooms.safetynetsalertsprojects.ControllerTest;


import com.openclassrooms.safetynetsalertsprojects.controller.FirestationsController;
import com.openclassrooms.safetynetsalertsprojects.dto.FirestationsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    private FirestationsController firestationsController;

    @Test
    public void shouldReturnListOfFirestation() throws Exception {

        Map<String,String> firestationsMap = new HashMap<>();

        firestationsMap.put("50 rue de java ","2");
        firestationsMap.put("20 rue du lac","1");

        when(firestationsController.getfirestationsmap()).thenReturn(firestationsMap);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/firestations"))
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

        when(firestationsController.findFirestationByStationNumber("2")).thenReturn(fireStationsList);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/stationNumber?station=station"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}


