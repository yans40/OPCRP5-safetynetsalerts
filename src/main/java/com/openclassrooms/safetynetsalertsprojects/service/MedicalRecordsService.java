package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import com.openclassrooms.safetynetsalertsprojects.repository.MedicalRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            medicalRecordsDto.setMedications(medicalRecords.getMedications());
            medicalRecordsDtoList.add(medicalRecordsDto);
        }
        return medicalRecordsDtoList;
    }


    public List<String> birthdays() {
        List<String> birthdayString = new ArrayList<>();
        List<MedicalRecords> personsList = medicalRecordsRepository.findAll();

        for (MedicalRecords md : personsList) {
            LocalDate dateOfBirth = LocalDate.parse(md.getBirthdate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            birthdayString.add(md.getBirthdate());
        }

        return birthdayString;
    }


    public Date birthdayStringToDate(String birthday) throws ParseException {

//        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        Date dateOfBirth = formatter.parse(birthday);
//        return dateOfBirth;
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);

        return date;


    }


    public int ageCalculator(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dateOfBirth);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        return age;


    }



}
