/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.patronage.edition_2022;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jklimaszewski
 */
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    public List<Reservation> findBySubjectId(Integer subjectId);

    public Reservation findBySpaceId(Integer spaceId);
}
