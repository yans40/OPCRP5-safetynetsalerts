package com.openclassrooms.safetynetsalertsprojects.ControllerTest;

import com.openclassrooms.safetynetsalertsprojects.controller.MedicalRecordsController;
import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    private MedicalRecordsController medicalRecordsController;

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

        when(medicalRecordsController.getAllMedicalRecords()).thenReturn(medicalRecordsList);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/medicalrecords")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3));
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

        when(medicalRecordsController.getAllMedicalRecords()).thenReturn(medicalRecordsList);
        List<MedicalRecordsDto> listToTest =medicalRecordsController.getAllMedicalRecords();
        assertEquals(3,listToTest.size());
    }

    @Test
    public void shouldCreateAnewMedicalRecord() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/medicalRecord")
                .content(String.valueOf((new MedicalRecordsDto())))
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldDeleteMedicalRecordsTest() {
        List<MedicalRecordsDto> medicalRecordsList = new ArrayList<>();
        //given
        MedicalRecordsDto medicalRecords = new MedicalRecordsDto("jean", "Robert", "10/02/1951", List.of("noxidian:100mg", "noznazol:250mg"), List.of(""));
        MedicalRecordsDto medicalRecords1 = new MedicalRecordsDto("Bruno", "BONNET", "14/03/1962", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        medicalRecordsList.add(medicalRecords);
        medicalRecordsList.add(medicalRecords1);
        //when
        when(medicalRecordsController.getAllMedicalRecords()).thenReturn(medicalRecordsList);
        medicalRecordsController.deleteMedicalRecord("jean", "Robert");
        //then
        verify(medicalRecordsController, times(1)).deleteMedicalRecord("jean", "Robert");
    }

    @Test
    public void shouldAddMedicalRecordsTest() throws Exception {
        List<MedicalRecordsDto> medicalRecordsList = new ArrayList<>();
        //given
        MedicalRecordsDto medicalRecords = new MedicalRecordsDto("jean", "Robert", "10/02/1951", List.of("noxidian:100mg", "noznazol:250mg"), List.of(""));
        MedicalRecordsDto medicalRecords1 = new MedicalRecordsDto("Bruno", "BONNET", "14/03/1962", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        medicalRecordsList.add(medicalRecords);

        //when
        when(medicalRecordsController.getAllMedicalRecords()).thenReturn(medicalRecordsList);
        medicalRecordsController.addNewMedicalRecord(medicalRecords1);
        //then
        verify(medicalRecordsController, times(1)).addNewMedicalRecord(medicalRecords1);
    }

}
