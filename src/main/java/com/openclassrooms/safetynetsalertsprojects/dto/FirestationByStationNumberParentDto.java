package com.openclassrooms.safetynetsalertsprojects.dto;

import java.util.List;

public class FirestationByStationNumberParentDto {

    private List<FirestationByStationNumberDto> personsInfo;
    private int nbAdult;
    private int nbChild;

    public FirestationByStationNumberParentDto(List<FirestationByStationNumberDto> personsInfo, int nbAdult, int nbChild) {
        this.personsInfo = personsInfo;
        this.nbAdult = nbAdult;
        this.nbChild = nbChild;
    }

    public FirestationByStationNumberParentDto() {

    }

    public List<FirestationByStationNumberDto> getPersonsInfo() {
        return personsInfo;
    }

    public void setPersonsInfo(List<FirestationByStationNumberDto> personsInfo) {
        this.personsInfo = personsInfo;
    }

    public int getNbAdult() {
        return nbAdult;
    }

    public void setNbAdult(int nbAdult) {
        this.nbAdult = nbAdult;
    }

    public int getNbChild() {
        return nbChild;
    }

    public void setNbChild(int nbChild) {
        this.nbChild = nbChild;
    }
}
