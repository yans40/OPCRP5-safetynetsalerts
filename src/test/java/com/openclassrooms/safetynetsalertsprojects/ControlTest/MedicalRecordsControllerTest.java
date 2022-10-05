package com.openclassrooms.safetynetsalertsprojects.ControlTest;

import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import org.junit.jupiter.api.Assertions;
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
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class MedicalRecordsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordsService medicalRecordsService;


    @Test
    public void shouldReturnListOfMedicalRecordsTest() throws Exception {
        List<MedicalRecordsDto> medicalRecordsList = new ArrayList<>();

        MedicalRecordsDto medicalRecords1 = new MedicalRecordsDto("Luc", "SANCHEZ", "25/12/1956", List.of("noxidian:100mg", "noznazol:250mg"), List.of(""));
        MedicalRecordsDto medicalRecords2 = new MedicalRecordsDto("Bruno", "BONNET", "14/03/1962", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        MedicalRecordsDto medicalRecords3 = new MedicalRecordsDto("Gerard", "BOUCHARD", "25/12/1956", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        medicalRecordsList.add(medicalRecords1);
        medicalRecordsList.add(medicalRecords2);
        medicalRecordsList.add(medicalRecords3);

        when(medicalRecordsService.getMedicalRecordsList()).thenReturn(medicalRecordsList);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/medicalrecords")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void MedicalrecordsListTest(){
        List<MedicalRecordsDto> medicalRecordsList = new ArrayList<>();

        MedicalRecordsDto medicalRecords1 = new MedicalRecordsDto("Luc", "SANCHEZ", "25/12/1956", List.of("noxidian:100mg", "noznazol:250mg"), List.of(""));
        MedicalRecordsDto medicalRecords2 = new MedicalRecordsDto("Bruno", "BONNET", "14/03/1962", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        MedicalRecordsDto medicalRecords3 = new MedicalRecordsDto("Gerard", "BOUCHARD", "25/12/1956", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        medicalRecordsList.add(medicalRecords1);
        medicalRecordsList.add(medicalRecords2);
        medicalRecordsList.add(medicalRecords3);

        when(medicalRecordsService.getMedicalRecordsList()).thenReturn(medicalRecordsList);

        Assertions.assertEquals(3, medicalRecordsList.size());
    }

    @Test
    public void shouldCreateAnewMedicalRecord() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/medicalRecord")
                .content(String.valueOf((new MedicalRecordsDto())))
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shoulDeleteAMedicalRecords() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/medicalrecords/delete/{firstName}&{lastName}","leo","messi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateAMedicalRecord() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/medicalrecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n    \"firstName\": \"leo\",\n    \"lastName\": \"Messi\",\n    \"birthdate\": \"09/06/1990\",\n    \"medications\": [],\n    \"allergies\": []\n}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateAPerson() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/medicalrecords/update/{firstName}&{lastName}", "leo", "messi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n    \"firstName\": \"leo\",\n    \"lastName\": \"Messi\",\n    \"birthdate\": \"09/06/1990\",\n    \"medications\": [],\n    \"allergies\": []\n}"))
                .andExpect(status().isOk());
    }
}
