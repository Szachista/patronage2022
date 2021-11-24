/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patronage.edition_2022;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    private Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private ParkingSpaceRepository parkingRepo;

    @Autowired
     SubjectRepository subjectRepo;

    public List<ParkingSpace> getReservationsBySubjectId(Integer subjectId) {
        if (subjectId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Niepoprawny id podmiotu!");

        if (subjectRepo.findBySubjectId(subjectId) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany podmiot nie istnieje!");

        return parkingRepo.findBySubjectId(subjectId);
    }

    public List<ParkingSpace> getEmptyParkingSpaces() {
        return parkingRepo.findEmptySpaces();
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

        if (parkingRepo.findBySpaceId(reservation.getSpaceId()) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podane miejsce parkingowe nie istnieje!");

        if (subjectRepo.findBySubjectId(reservation.getSubjectId()) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany podmiot nie istnieje!");

        reservationRepo.save(reservation);
    }

    public Subject addSubject(String subjectName) {
        if (subjectName == null || subjectName.isEmpty() || subjectName.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brak nazwy podmiotu!");

        if (subjectName.length() < 2 || subjectName.length() > 20)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nazwa musi mieć długość od 2 do 20 znaków!");

        if (!subjectName.matches("\\w+(\\s\\w+)*"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, subjectName + ": Nazwa może składać się wyłącznie z liter alfabetu łacińskiego, cyfr, znaku podkreślenia, a pomiędzy wyrazami może być wyłącznie pojedynczy odstęp!");

        Subject found = subjectRepo.findBySubjectNameIgnoreCase(subjectName);
        if (found != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nazwa zajęta! Wybierz inną!");

        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        return subjectRepo.save(subject);
    }
}
