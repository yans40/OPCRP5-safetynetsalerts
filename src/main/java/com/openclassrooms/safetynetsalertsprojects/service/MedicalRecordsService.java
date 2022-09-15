package com.openclassrooms.safetynetsalertsprojects.service;

import com.openclassrooms.safetynetsalertsprojects.dto.MedicalRecordsDto;
import com.openclassrooms.safetynetsalertsprojects.model.MedicalRecords;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
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

    public void addNewMedicalRecord(MedicalRecordsDto medicalRecordsDto) {
        MedicalRecords medicalRecords = dtoToMedicalRecords(medicalRecordsDto);
        medicalRecordsRepository.save(medicalRecords);
    }

    public void updateMedicalRecord(String firstName, String lastName, MedicalRecordsDto medicalRecordsDto) {
        MedicalRecordsDto medicalRecordsDto1 = findMedicalRecordsByFirstNameAndLastName(firstName, lastName);
        settingMedicalRecordsChanges(medicalRecordsDto1, medicalRecordsDto);
        MedicalRecords medicalRecords = dtoToMedicalRecords(medicalRecordsDto1);
        int index = getMedicalecordsIndex(medicalRecordsDto1);
        medicalRecordsRepository.update(index, medicalRecords);
    }

    private int getMedicalecordsIndex(MedicalRecordsDto medicalRecordsDto1) {
        List<MedicalRecords> list = medicalRecordsRepository.findAll();
        int index = 0;
        for (MedicalRecords medicalRecords : list) {
            if (medicalRecords.getFirstName().equals(medicalRecordsDto1.getFirstName()) && medicalRecords.getLastName().equals(medicalRecordsDto1.getLastName())) {
                index = list.lastIndexOf(medicalRecords);
            }
        }
        return index;
    }

    public MedicalRecords dtoToMedicalRecords(MedicalRecordsDto medicalRecordsDto1) {
        return new MedicalRecords(medicalRecordsDto1.getFirstName(), medicalRecordsDto1.getLastName(), medicalRecordsDto1.getBirthdate(), medicalRecordsDto1.getMedications(), medicalRecordsDto1.getAllergies());
    }

    public void settingMedicalRecordsChanges(MedicalRecordsDto md1, MedicalRecordsDto md2) {
        md1.setFirstName(md2.getFirstName());
        md1.setLastName(md2.getLastName());
        md1.setBirthdate(md2.getBirthdate());
        md1.setMedications(md2.getMedications());
        md1.setAllergies(md2.getAllergies());
    }


    public MedicalRecordsDto findMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {
        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.findAll();
        MedicalRecordsDto medicalRecordsDto = new MedicalRecordsDto();

        for (MedicalRecords md : medicalRecordsList) {

            if (md.getFirstName().equals(firstName) && md.getLastName().equals(lastName)) {
                medicalRecordsDto.setFirstName(md.getFirstName());
                medicalRecordsDto.setLastName(md.getLastName());
                medicalRecordsDto.setBirthdate(md.getBirthdate());
                medicalRecordsDto.setAllergies(md.getAllergies());
                medicalRecordsDto.setMedications(md.getMedications());
            }
        }
        return medicalRecordsDto;
    }

    public MedicalRecords findMedicalRecord(String firstName, String lastName) {
        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.findAll();

        for (MedicalRecords md : medicalRecordsList) {

            if (md.getFirstName().equals(firstName) && md.getLastName().equals(lastName)) {

                return md;
            }
        }
        return null;
    }


    public List<MedicalRecordsDto> getMedicalRecordsList() {

        List<MedicalRecords> medicalRecordsList = medicalRecordsRepository.findAll();
        List<MedicalRecordsDto> medicalRecordsDtoList = new ArrayList<>();

        for (MedicalRecords medicalRecords : medicalRecordsList) {
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

    public void deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecords medicalRecordTodelete = findMedicalRecord(firstName, lastName);
        if (medicalRecordTodelete != null) {
            medicalRecordsRepository.delete(medicalRecordTodelete);
        }
    }
}
