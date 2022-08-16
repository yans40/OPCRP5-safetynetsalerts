package com.openclassrooms.safetynetsalertsprojects.ControllerTest;

import com.openclassrooms.safetynetsalertsprojects.controller.PersonsController;
import com.openclassrooms.safetynetsalertsprojects.dto.FirestationByStationNumberDto;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonsControllerTest {


    @Mock
    private PersonsService personsService;
    @InjectMocks
    private PersonsController personsController;



    @Test
    void shouldReturnListOfPersons() throws Exception {

        List<FirestationByStationNumberDto> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        FirestationByStationNumberDto alainB = new FirestationByStationNumberDto("Alain", "BOUCHON", "01 rue du Mock", "0101010101");
        FirestationByStationNumberDto bernardK = new FirestationByStationNumberDto("Bernard", "KARL", "50 rue de java", "0202020202");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);

        when(personsService.getPersonList()).thenReturn(mylistOfFirestationsBystationNumberDto);

        assertEquals(2,personsController.showPersonsList().size());
        assertEquals("Bernard",personsController.showPersonsList().get(1).getFirstName());

    }

    @Test
    void shouldReturnListOfPersonsByAddress(){
        List<FirestationByStationNumberDto> mylistOfFirestationsBystationNumberDto = new ArrayList<>();

        FirestationByStationNumberDto alainB = new FirestationByStationNumberDto("Alain", "BOUCHON", "01 rue du Mock", "0101010101");
        FirestationByStationNumberDto bernardK = new FirestationByStationNumberDto("Bernard", "KARL", "50 rue de java", "0202020202");
        FirestationByStationNumberDto jenniferF= new FirestationByStationNumberDto("jennifer","FOREST","10 avenue du general","0303030303");

        mylistOfFirestationsBystationNumberDto.add(alainB);
        mylistOfFirestationsBystationNumberDto.add(bernardK);
        mylistOfFirestationsBystationNumberDto.add(jenniferF);

        when(personsService.getPersonsByAddress("10 avenue du general")).thenReturn(Collections.singletonList(mylistOfFirestationsBystationNumberDto.get(2)));

        assertEquals(1,personsController.findPersonsByAddress("10 avenue du general").size());
        assertEquals("jennifer",personsController.findPersonsByAddress("10 avenue du general").get(0).getFirstName());


    }

    @Test
    void shouldReturnListOfPersonsByStationNumber(){



    }
}
