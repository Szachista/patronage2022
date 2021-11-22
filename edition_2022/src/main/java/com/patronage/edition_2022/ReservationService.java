/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patronage.edition_2022;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author jklimaszewski
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private ParkingSpaceRepository parkingRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    public List<ParkingSpace> getReservationsBySubjectId(Integer subjectId) {
        if (subjectRepo.findBySubjectId(subjectId) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany podmiot nie istnieje!");

        return parkingRepo.findBySubjectId(subjectId);
    }

    public List<ParkingSpace> getEmptyParkingSpaces() {
        return parkingRepo.findEmptySpaces();
    }

    public void removeReservation(Reservation reservation) {
        Reservation found = reservationRepo.findBySpaceId(reservation.getSpaceId());

        if (found == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podana rezerwacja nie istnieje!");

        if (!found.getSubjectId().equals(reservation.getSubjectId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nie jesteś właścicielem podanej rezerwacji!");

        reservationRepo.delete(reservation);
    }

    public void addReservation(Reservation reservation) {
        Reservation found = reservationRepo.findBySpaceId(reservation.getSpaceId());

        if (found != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Miejsce parkingowe zostało już wcześniej zajęte!");

        if (subjectRepo.findBySubjectId(reservation.getSubjectId()) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany podmiot nie istnieje!");

        reservationRepo.save(reservation);
    }
}
