package com.openclassrooms.safetynetsalertsprojects.dto;


public class FirestationByStationNumberDto {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private Integer age;

//    private Integer adulteNumber;  essayer le comptage projet 2
//
//    private Integer mineurNumber;



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


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
