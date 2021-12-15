/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.patronage.edition_2022.repository;

import com.patronage.edition_2022.model.ParkingSpace;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jklimaszewski
 */
@Repository
public interface ParkingSpaceRepository extends CrudRepository<ParkingSpace, Integer> {

    @Query(value = "SELECT S.SPACE_ID, S.SPACE_LEVEL, S.SPACE_NUMBER, S.FOR_DISABLED FROM PARKING_SPACE S LEFT JOIN RESERVATION R ON S.SPACE_ID = R.SPACE_ID WHERE SUBJECT_ID IS NULL", nativeQuery = true)
    public List<ParkingSpace> findEmptySpaces();

    @Query(value = "SELECT S.SPACE_ID, S.SPACE_LEVEL, S.SPACE_NUMBER, S.FOR_DISABLED FROM PARKING_SPACE S LEFT JOIN RESERVATION R ON S.SPACE_ID = R.SPACE_ID WHERE SUBJECT_ID = ?1", nativeQuery = true)
    public List<ParkingSpace> findBySubjectId(Integer subjectId);

    public ParkingSpace findBySpaceId(Integer spaceId);
}
