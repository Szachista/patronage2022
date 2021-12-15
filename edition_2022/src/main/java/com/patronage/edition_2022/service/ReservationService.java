/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patronage.edition_2022.service;

import com.patronage.edition_2022.model.ParkingSpace;
import com.patronage.edition_2022.model.Reservation;
import com.patronage.edition_2022.repository.ReservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author jklimaszewski
 */
@Service
public class ReservationService {

//    private Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private ParkingSpaceService parkingService;

    @Autowired
    private SubjectService subjectService;

    public List<ParkingSpace> getReservationsBySubjectId(@NonNull Integer subjectId) {
        if (!subjectService.isSubjectExisting(subjectId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany podmiot nie istnieje!");

        return parkingService.getBySubjectId(subjectId);
    }

    public List<ParkingSpace> getEmptyParkingSpaces() {
        return parkingService.getEmptySpaces();
    }

    public void removeReservation(Reservation reservation) {
        if (reservation.getSpaceId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Niepoprawny id miejsca parkingowego!");

        if (reservation.getSubjectId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Niepoprawny id podmiotu!");

        Reservation found = reservationRepo.findBySpaceId(reservation.getSpaceId());

        if (found == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podana rezerwacja nie istnieje!");

        if (!found.getSubjectId().equals(reservation.getSubjectId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nie jesteś właścicielem podanej rezerwacji!");

        reservationRepo.delete(reservation);
    }

    public void addReservation(Reservation reservation) {
        if (reservation.getSpaceId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Niepoprawny id miejsca parkingowego!");

        if (reservation.getSubjectId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Niepoprawny id podmiotu!");

        if (reservationRepo.findBySpaceId(reservation.getSpaceId()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Miejsce parkingowe zostało już wcześniej zajęte!");

        if (!parkingService.isSpaceExisting(reservation.getSpaceId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podane miejsce parkingowe nie istnieje!");

        if (!subjectService.isSubjectExisting(reservation.getSubjectId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany podmiot nie istnieje!");

        reservationRepo.save(reservation);
    }
}
