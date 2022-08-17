package com.openclassrooms.safetynetsalertsprojects.dto;


import java.util.List;

public class FirestationByStationNumberDto {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public FirestationByStationNumberDto(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public FirestationByStationNumberDto() {

    }

    public FirestationByStationNumberDto(List<FirestationByStationNumberDto> mylistOfFirestationsBystationNumberDto, int nbAdult, int nbChild) {
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


}
