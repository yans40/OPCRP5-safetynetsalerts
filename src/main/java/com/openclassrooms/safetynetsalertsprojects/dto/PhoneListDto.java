package com.openclassrooms.safetynetsalertsprojects.dto;

import java.util.List;

public class PhoneListDto {

  String phone;

    public PhoneListDto(String phone) {
        this.phone = phone;
    }

    public PhoneListDto() {

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
