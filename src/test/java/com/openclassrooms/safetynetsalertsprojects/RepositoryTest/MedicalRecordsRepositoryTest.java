package com.openclassrooms.safetynetsalertsprojects.RepositoryTest;


import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import com.openclassrooms.safetynetsalertsprojects.repository.MedicalRecordsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class MedicalRecordsRepositoryTest {

    @Mock
    DataSource dataSource;

    @InjectMocks
    private MedicalRecordsRepository medicalRecordsRepository;



    @Test
    public void findAllTest() {
        List<MedicalRecords> mylistOfMedicalRecords = new ArrayList<>();


        MedicalRecords medicalRecords1 = new MedicalRecords("Alain","BOUCHER","25/12/1956",List.of("hydrapermazol:900mg","thradox:700mg"),List.of("peanut", "shellfish", "aznol"));
        MedicalRecords medicalRecords2=new MedicalRecords("Bernard","VOISIN","25/12/1956",List.of("aznol:200mg","noxidian:100mg"),List.of("nillacilan"));
        MedicalRecords medicalRecords3=new MedicalRecords("Luc","SANCHEZ","25/12/1956",List.of("noxidian:100mg","noznazol:250mg"),List.of(""));


        mylistOfMedicalRecords.add(medicalRecords1);
        mylistOfMedicalRecords.add(medicalRecords2);
        mylistOfMedicalRecords.add(medicalRecords3);



        when(dataSource.getMedicalrecords()).thenReturn(mylistOfMedicalRecords);
        List<MedicalRecords> laMedicalRecordsListe = medicalRecordsRepository.findAll();

        assertEquals(3, laMedicalRecordsListe.size());
        assertEquals("Alain", medicalRecords1.getFirstName());
        verify(dataSource,times(1)).getMedicalrecords();

    }

}


