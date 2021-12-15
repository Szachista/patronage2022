/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patronage.edition_2022.service;

import com.patronage.edition_2022.repository.ParkingSpaceRepository;
import java.util.List;
import com.patronage.edition_2022.model.ParkingSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jklimaszewski
 */
@Service
public class ParkingSpaceService {
    @Autowired
    ParkingSpaceRepository parkingRepository;

    List<ParkingSpace> getBySubjectId(Integer subjectId) {
        return parkingRepository.findBySubjectId(subjectId);
    }

    List<ParkingSpace> getEmptySpaces() {
        return parkingRepository.findEmptySpaces();
    }

    boolean isSpaceExisting(Integer spaceId) {
        return parkingRepository.findBySpaceId(spaceId) != null;
    }
}
