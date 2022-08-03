package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.dto.FireByAddressDto;
import com.openclassrooms.safetynetsalertsprojects.dto.FirestationByStationNumberDto;
import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import com.openclassrooms.safetynetsalertsprojects.repository.MedicalRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MedicalRecordsService {
    @Autowired
    private MedicalRecordsRepository medicalRecordsRepository;


    public List<MedicalRecordsDto> getMedicalRecordsList() {

        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.findAll();
        List<MedicalRecordsDto> medicalRecordsDtoList = new ArrayList<>();

        for(MedicalRecords medicalRecords : medicalRecordsList){
            MedicalRecordsDto medicalRecordsDto = new MedicalRecordsDto();
            medicalRecordsDto.setFirstName(medicalRecords.getFirstName());
            medicalRecordsDto.setLastName(medicalRecords.getLastName());
            medicalRecordsDto.setBirthdate(medicalRecords.getBirthdate());
            medicalRecordsDto.setAllergies(medicalRecords.getAllergies());
            medicalRecordsDto.setMedications(medicalRecords.getMedications());
            medicalRecordsDtoList.add(medicalRecordsDto);
        }
        return medicalRecordsDtoList;
    }


    public Date birthdayStringToDate(String birthday) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
        return date;
    }

    public int ageGenerator(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dateOfBirth);
        return today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
    }

    public int ageCalculator(MedicalRecordsDto medicalRecordsDto) throws ParseException {
        String dateNaissance = medicalRecordsDto.getBirthdate();
        Date persAge = birthdayStringToDate(dateNaissance);
        int age = ageGenerator(persAge);
        return age;
    }

}
