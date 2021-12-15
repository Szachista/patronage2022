/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patronage.edition_2022.dto;

/**
 *
 * @author jklimaszewski
 */
public class ParkingSpaceDTO {
    private Integer spaceNumber;

    private Integer spaceLevel;

    private Boolean forDisabled;

    public Integer getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(Integer spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public Integer getSpaceLevel() {
        return spaceLevel;
    }

    public void setSpaceLevel(Integer spaceLevel) {
        this.spaceLevel = spaceLevel;
    }

    public Boolean getForDisabled() {
        return forDisabled;
    }

    public void setForDisabled(Boolean forDisabled) {
        this.forDisabled = forDisabled;
    }
}
