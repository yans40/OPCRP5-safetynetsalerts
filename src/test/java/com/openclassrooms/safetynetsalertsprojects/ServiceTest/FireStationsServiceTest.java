package com.openclassrooms.safetynetsalertsprojects.ServiceTest;

import com.openclassrooms.safetynetsalertsprojects.dto.FirestationsDto;
import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.repository.FireStationsRepository;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FireStationsServiceTest {

    @Mock
    FireStationsRepository fireStationsRepository;

    @InjectMocks
    FireStationsService fireStationsService;


    @Test
    void getFireStationsListTest() {

        List<FireStations> mylistOfFirestations = new ArrayList<>();

        FireStations fireStations1 = new FireStations("50 rue de java ", "2");
        FireStations fireStations2 = new FireStations("20 rue du lac", "1");

        mylistOfFirestations.add(fireStations1);
        mylistOfFirestations.add(fireStations2);


        when(fireStationsRepository.findAll()).thenReturn(mylistOfFirestations);
        List<FirestationsDto> firestationsDtoList = fireStationsService.getFireStationsList();

        assertEquals(2, firestationsDtoList.size());
        assertNotNull(fireStations1.getStation());
        verify(fireStationsRepository, times(1)).findAll();

    }
}
