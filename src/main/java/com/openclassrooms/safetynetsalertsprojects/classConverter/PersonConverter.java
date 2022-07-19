package com.openclassrooms.safetynetsalertsprojects.classConverter;
import com.openclassrooms.safetynetsalertsprojects.dto.FirestationByStationNumberDto;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import org.modelmapper.ModelMapper;

public class PersonConverter {

        public FirestationByStationNumberDto convertEntityToDto(Persons persons) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(persons, FirestationByStationNumberDto.class);
        }
}
