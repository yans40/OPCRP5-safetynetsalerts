package com.openclassrooms.safetynetsalertsprojects.RepositoryTest;

import com.openclassrooms.safetynetsalertsprojects.model.DataSource;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.repository.PersonsRepository;
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

public class PersonsRepositoryTest {

    @Mock
    DataSource dataSource;

    @InjectMocks
    private PersonsRepository personsRepository;


    @Test
    public void findAllTest() {
        List<Persons> mylistOfPersons = new ArrayList<>();

        Persons person1 = new Persons("Alain", "BOUCHON", "01 rue du Mock", "Nanterre", "0101010101", "alainb@test.fr", "92000");
        Persons person2 = new Persons("Bernard", "KARL", "50 rue de java ", "Paris", "0202020202", "BernardK@test.fr", "75000");
        Persons person3 = new Persons("Charles", "HENRY", "30 rue de Spring", "Creteil", "0303030303", "springC@test.fr", "77000");

        mylistOfPersons.add(person1);
        mylistOfPersons.add(person2);
        mylistOfPersons.add(person3);

        when(dataSource.getPersons()).thenReturn(mylistOfPersons);
        List<Persons> laListe = personsRepository.findAll();

        assertEquals(3, laListe.size());
        assertEquals("Bernard", person2.getFirstName());
        verify(dataSource, times(1)).getPersons();

    }

}
