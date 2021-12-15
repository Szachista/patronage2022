/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patronage.edition_2022.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jklimaszewski
 */
@Entity
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spaceId;

    @Column(nullable = false)
    private Integer spaceNumber;

    @Column(nullable = false)
    private Integer spaceLevel;

    @Column(nullable = false)
    private Boolean forDisabled;

    public ParkingSpace() {
        //
    }

    public ParkingSpace(Integer spaceId, Integer spaceNumber, Integer spaceLevel, Boolean forDisabled) {
        this.spaceId = spaceId;
        this.spaceNumber = spaceNumber;
        this.spaceLevel = spaceLevel;
        this.forDisabled = forDisabled;
    }

    public ParkingSpace(ParkingSpace space) {
        this.spaceId = space.spaceId;
        this.spaceNumber = space.spaceNumber;
        this.spaceLevel = space.spaceLevel;
        this.forDisabled = space.forDisabled;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

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
