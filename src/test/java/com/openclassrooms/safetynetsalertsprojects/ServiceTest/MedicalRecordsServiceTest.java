package com.openclassrooms.safetynetsalertsprojects.ServiceTest;

import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import com.openclassrooms.safetynetsalertsprojects.repository.MedicalRecordsRepository;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordsServiceTest {
    @Mock
    MedicalRecordsRepository medicalRecordsRepository;

    @InjectMocks
    MedicalRecordsService medicalRecordsService;


    @Test
    void getMedicalRecordsListTest() {

        List<MedicalRecords> mylistOfMedicalRecords = new ArrayList<>();


        MedicalRecords medicalRecords1 = new MedicalRecords("Alain", "BOUCHER", "25/12/1956", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        MedicalRecords medicalRecords2 = new MedicalRecords("Bernard", "VOISIN", "25/12/1956", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));
        MedicalRecords medicalRecords3 = new MedicalRecords("Luc", "SANCHEZ", "25/12/1956", List.of("noxidian:100mg", "noznazol:250mg"), List.of(""));

        mylistOfMedicalRecords.add(medicalRecords1);
        mylistOfMedicalRecords.add(medicalRecords2);
        mylistOfMedicalRecords.add(medicalRecords3);

        when(medicalRecordsRepository.findAll()).thenReturn(mylistOfMedicalRecords);
        List<MedicalRecordsDto> medicalRecordsDtoList = medicalRecordsService.getMedicalRecordsList();

        assertEquals(3, medicalRecordsDtoList.size());
        assertNotNull(medicalRecords3.getMedications());
        verify(medicalRecordsRepository, times(1)).findAll();


    }


    @Test
    void ageCalculatorTest() throws ParseException {

        MedicalRecordsDto medicalRecordsDto = new MedicalRecordsDto("bernard", "BOUCHER", "12/01/1980", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));

        int age = medicalRecordsService.ageCalculator(medicalRecordsDto);
        assertNotNull(age);
        assertEquals(age, 42);

    }

    @Test
    void findMedicalRecordsTest() {

        MedicalRecords medicalRecords1 = new MedicalRecords("Alain", "BOUCHER", "25/12/1956", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        MedicalRecords medicalRecords2 = new MedicalRecords("Bernard", "VOISIN", "25/12/1956", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        medicalRecordsService.findMedicalRecord("Alain","Boucher");



    }

    @Test
    void settingMedicalChangesTest() {

        MedicalRecordsDto medicalRecords1 = new MedicalRecordsDto("Alain", "BOUCHER", "25/12/1956", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        MedicalRecordsDto medicalRecords2 = new MedicalRecordsDto("Bernard", "VOISIN", "25/12/1956", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        medicalRecordsService.settingMedicalRecordsChanges(medicalRecords1, medicalRecords2);

        assertThat(medicalRecords1.getFirstName().equals(medicalRecords2.getFirstName()));

    }


}
