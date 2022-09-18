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
    public void getFireStationsListTest() {

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


    @Test
    public void getFirestationByStationNumber() {
        List<FireStations> mylistOfFirestations = new ArrayList<>();

        FireStations fireStations1 = new FireStations("50 rue de java ", "2");
        FireStations fireStations2 = new FireStations("20 rue du lac", "1");

        mylistOfFirestations.add(fireStations1);
        mylistOfFirestations.add(fireStations2);

        when(fireStationsRepository.findAll()).thenReturn(mylistOfFirestations);
        List<FirestationsDto> listToTest = fireStationsService.getFirestationByStationNumber("2");

        assertEquals(1, listToTest.size());
        assertEquals(listToTest.get(0).getAddress(), "50 rue de java ");
        verify(fireStationsRepository, times(1)).findAll();
    }

    @Test
    public void updateFirestationtest() {

        FirestationsDto fireStationsDto = new FirestationsDto("50 rue de java", "2");

        fireStationsService.updateFirestation("20 rue du lac", fireStationsDto);

        verify(fireStationsRepository, times(1)).findAll();
    }

    @Test
    public void findFirestationTest() {
        List<FireStations> mylistOfFirestations = new ArrayList<>();

        FireStations fireStations1 = new FireStations("50 rue de java", "2");
        FireStations fireStations2 = new FireStations("20 rue du lac", "1");

        mylistOfFirestations.add(fireStations1);
        mylistOfFirestations.add(fireStations2);

        when(fireStationsRepository.findAll()).thenReturn(mylistOfFirestations);
        String address = "50 rue de java";
        FirestationsDto firestationsDto = fireStationsService.findFirestationByAddress("50 rue de java");
        assertEquals(address, firestationsDto.getAddress());
    }

    @Test
    public void settingFirestationChangesTest() {
        FirestationsDto fireStations1 = new FirestationsDto("50 rue de java", "2");
        FirestationsDto fireStations2 = new FirestationsDto("20 rue du lac", "1");

        fireStationsService.settingFirestationChanges(fireStations1, fireStations2);

        assertEquals(fireStations1.getStation(), fireStations2.getStation());
    }

    @Test
    public void getFireStationHashMapTest() {
        List<FireStations> mylistOfFirestations = new ArrayList<>();

        FireStations fireStations1 = new FireStations("50 rue de java", "2");
        FireStations fireStations2 = new FireStations("20 rue du lac", "1");

        mylistOfFirestations.add(fireStations1);
        mylistOfFirestations.add(fireStations2);

        when(fireStationsRepository.findAll()).thenReturn(mylistOfFirestations);
        fireStationsService.getFireStationsHashMap();

        verify(fireStationsRepository, times(1)).findAll();
    }
}
