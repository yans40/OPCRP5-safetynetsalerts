package com.openclassrooms.safetynetsalertsprojects.dto;

public class FirestationsDto {

    public FirestationsDto(String address, String station) {
        this.address = address;
        this.station = station;
    }

    private String address;
    private String station;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
