package com.openclassrooms.safetynetsalertsprojects.RepositoryTest;


import com.openclassrooms.safetynetsalertsprojects.dto.PersonDto;
import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.repository.FireStationsRepository;
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
public class FirestationsRepositoryTest {

    @Mock
    private DataSource dataSource;

    @InjectMocks
    private FireStationsRepository fireStationsRepository;


    @Test
    public void findAllTest() {
        List<FireStations> mylistOfFirestations = new ArrayList<>();

        FireStations fireStations1 = new FireStations("50 rue de java ", "2");
        FireStations fireStations2 = new FireStations("20 rue du lac", "1");

        mylistOfFirestations.add(fireStations1);
        mylistOfFirestations.add(fireStations2);


        when(dataSource.getFirestations()).thenReturn(mylistOfFirestations);
        List<FireStations> laFirestationsListe = fireStationsRepository.findAll();

        assertEquals(2, laFirestationsListe.size());
        assertEquals("1", fireStations2.getStation());
        verify(dataSource, times(1)).getFirestations();
    }

    @Test
    public void saveTest() {
        List<FireStations> mylistOfFirestations = new ArrayList<>();

        FireStations fireStations1 = new FireStations("50 rue de java ", "2");
        FireStations fireStations2 = new FireStations("20 rue du lac", "1");

        mylistOfFirestations.add(fireStations1);
        mylistOfFirestations.add(fireStations2);

        when(dataSource.getFirestations()).thenReturn(mylistOfFirestations);
        FireStations fireStations3 = new FireStations("10 rue de paris", "3");
        fireStationsRepository.save(fireStations3);

        assertEquals(3, mylistOfFirestations.size());
    }

    @Test
    public void deleteTest() {
        List<FireStations> mylistOfFirestations = new ArrayList<>();

        FireStations fireStations1 = new FireStations("50 rue de java ", "2");
        FireStations fireStations2 = new FireStations("20 rue du lac", "1");
        FireStations fireStations3 = new FireStations("10 rue de paris", "3");

        mylistOfFirestations.add(fireStations1);
        mylistOfFirestations.add(fireStations2);
        mylistOfFirestations.add(fireStations3);

        when(dataSource.getFirestations()).thenReturn(mylistOfFirestations);

        fireStationsRepository.delete(fireStations2);

        assertEquals(2, mylistOfFirestations.size());
    }
}


