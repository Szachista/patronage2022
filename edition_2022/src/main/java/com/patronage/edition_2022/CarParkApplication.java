package com.patronage.edition_2022;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@SpringBootApplication
public class CarParkApplication {

    @Autowired
    ReservationService service;

    @GetMapping("/carpark/reservations/{subjectId}")
    @ResponseBody
    List<ParkingSpace> getReservations(@PathVariable int subjectId) {
        return service.getReservationsBySubjectId(subjectId);
    }

    @GetMapping("/carpark/emptyspaces")
    @ResponseBody
    List<ParkingSpace> getEmptySpaces() {
        return service.getEmptyParkingSpaces();
    }

    @DeleteMapping("/carpark/reservations")
    @ResponseStatus(value = HttpStatus.OK, reason = "Rezerwacja usunięta pomyślnie!")
    void removeReservation(@RequestBody Reservation reservation) {
        service.removeReservation(reservation);
    }

    @PostMapping("/carpark/reservations")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Rezerwacja dokonana pomyślnie!")
    void addReservation(@RequestBody Reservation reservation) {
        service.addReservation(reservation);
    }

    public static void main(String[] args) {
        SpringApplication.run(CarParkApplication.class, args);
    }

}
