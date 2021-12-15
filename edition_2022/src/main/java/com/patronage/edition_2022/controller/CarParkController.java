/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patronage.edition_2022.controller;

import com.patronage.edition_2022.dto.SubjectDTO;
import com.patronage.edition_2022.model.ParkingSpace;
import com.patronage.edition_2022.model.Reservation;
import com.patronage.edition_2022.model.Subject;
import com.patronage.edition_2022.service.ReservationService;
import com.patronage.edition_2022.service.SubjectService;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jklimaszewski
 */
@RestController
public class CarParkController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/carpark/reservations/{subjectId}")
    List<ParkingSpace> getReservations(@PathVariable int subjectId) {
        return reservationService.getReservationsBySubjectId(subjectId);
    }

    @GetMapping("/carpark/emptyspaces")
    List<ParkingSpace> getEmptySpaces() {
        return reservationService.getEmptyParkingSpaces();
    }

    @DeleteMapping("/carpark/reservations")
    @ResponseStatus(value = HttpStatus.OK, reason = "Rezerwacja usunięta pomyślnie!")
    void removeReservation(@RequestBody Reservation reservation) {
        reservationService.removeReservation(reservation);
    }

    @PostMapping("/carpark/reservations")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Rezerwacja dokonana pomyślnie!")
    void addReservation(@RequestBody Reservation reservation) {
        reservationService.addReservation(reservation);
    }

    @PostMapping("/carpark/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    Subject addSubject(@RequestBody @Valid SubjectDTO subject) {
        return subjectService.createSubject(subject.getSubjectName());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Brak obiektu!")
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public void bodyMissing() {
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String customMessage(ConstraintViolationException e) {
        return e.getMessage();
    }
}
