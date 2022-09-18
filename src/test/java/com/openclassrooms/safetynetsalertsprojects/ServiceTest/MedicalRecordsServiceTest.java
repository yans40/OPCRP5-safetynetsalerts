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
        assertEquals(age, 42);

    }

    @Test
    void findMedicalRecordsTest() {
        List<MedicalRecords> mylistOfMedicalRecords = new ArrayList<>();

        MedicalRecords medicalRecords1 = new MedicalRecords("Alain", "BOUCHER", "14/03/1962", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        MedicalRecords medicalRecords2 = new MedicalRecords("Bernard", "VOISIN", "25/12/1956", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        mylistOfMedicalRecords.add(medicalRecords1);
        mylistOfMedicalRecords.add(medicalRecords2);

        when(medicalRecordsRepository.findAll()).thenReturn(mylistOfMedicalRecords);
        MedicalRecords medicalRecords3 = medicalRecordsService.findMedicalRecord("Alain", "BOUCHER");

        verify(medicalRecordsRepository, times(1)).findAll();
        assertEquals(medicalRecords3.getMedications(), medicalRecords1.getMedications());

    }

    @Test
    void getMedicalecordsIndexTest() {

        List<MedicalRecords> mylistOfMedicalRecords = new ArrayList<>();

        MedicalRecords medicalRecords1 = new MedicalRecords("Luc", "SANCHEZ", "25/12/1956", List.of("noxidian:100mg", "noznazol:250mg"), List.of(""));
        MedicalRecords medicalRecords2 = new MedicalRecords("Bruno", "BONNET", "14/03/1962", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        MedicalRecords medicalRecords3 = new MedicalRecords("Gerard", "BOUCHARD", "25/12/1956", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        mylistOfMedicalRecords.add(medicalRecords1);
        mylistOfMedicalRecords.add(medicalRecords2);
        mylistOfMedicalRecords.add(medicalRecords3);

        MedicalRecordsDto medicalRecordsdto = new MedicalRecordsDto("Bruno", "BONNET", "14/03/1962", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));

        when(medicalRecordsRepository.findAll()).thenReturn(mylistOfMedicalRecords);
        int index = medicalRecordsService.getMedicalecordsIndex(medicalRecordsdto);

        assertEquals(1, index);

    }

    @Test
    void settingMedicalChangesTest() {

        MedicalRecordsDto medicalRecords1 = new MedicalRecordsDto("Alain", "BOUCHER", "25/12/1956", List.of("hydrapermazol:900mg", "thradox:700mg"), List.of("peanut", "shellfish", "aznol"));
        MedicalRecordsDto medicalRecords2 = new MedicalRecordsDto("Bernard", "VOISIN", "25/12/1956", List.of("aznol:200mg", "noxidian:100mg"), List.of("nillacilan"));

        medicalRecordsService.settingMedicalRecordsChanges(medicalRecords1, medicalRecords2);

        assertEquals(medicalRecords1.getFirstName(), medicalRecords2.getFirstName());
    }

}
