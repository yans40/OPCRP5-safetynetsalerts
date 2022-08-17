package com.openclassrooms.safetynetsalertsprojects.dto;

import java.util.List;

public class ChildAlertListAndFamilyDto {
    List<ChildAlertByAddressDto> childAlertByAdressDtoList;
    List<ParentListByAdressDto> parentListByAdressDtoList;

    public ChildAlertListAndFamilyDto(List<ChildAlertByAddressDto> childAlertByAdressDtoList, List<ParentListByAdressDto> parentListByAdressDtoList) {
        this.childAlertByAdressDtoList = childAlertByAdressDtoList;
        this.parentListByAdressDtoList = parentListByAdressDtoList;
    }

    public ChildAlertListAndFamilyDto() {

    }

    public List<ChildAlertByAddressDto> getChildAlertByAdressDtoList() {
        return childAlertByAdressDtoList;
    }

    public void setChildAlertByAdressDtoList(List<ChildAlertByAddressDto> childAlertByAdressDtoList) {
        this.childAlertByAdressDtoList = childAlertByAdressDtoList;
    }

    public List<ParentListByAdressDto> getParentListByAdressDtoList() {
        return parentListByAdressDtoList;
    }

    public void setParentListByAdressDtoList(List<ParentListByAdressDto> parentListByAdressDtoList) {
        this.parentListByAdressDtoList = parentListByAdressDtoList;
    }
}
