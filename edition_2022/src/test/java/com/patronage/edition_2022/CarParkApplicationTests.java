package com.patronage.edition_2022;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CarParkApplicationTests {

    @Autowired
    CarParkApplication controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void signUpWithEmptySubjectNameShouldFail() {
        Subject subject = new Subject();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void singUpWithBlankSubjectNameShouldFail() {
        Subject subject = new Subject();
        subject.setSubjectName("\n \t\r");
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpWithTooShortSubjectNameShouldFail() {
        Subject subject = new Subject();
        subject.setSubjectName("J");
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpWithTooLongSubjectNameShouldFail() {
        Subject subject = new Subject();
        subject.setSubjectName("Zachodniopomorski Uniwersytet Technologiczny w Szczecinie");
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpWithNonLatinSubjectNameShouldFail() {
        Subject subject = new Subject();
        subject.setSubjectName("Szczeciński");
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpWithValidSubjectNameShouldPass() {
        Subject subject = new Subject();
        subject.setSubjectName("intive");
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
    }

    @Test
    public void signUpWithExistingSubjectNameShouldFail() {
        Subject subject = new Subject();
        subject.setSubjectName("intive_");
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);

        subject.setSubjectName("intive_");
        response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void signUpWithCaseInsensitiveCompareShouldFail() {
        // chyba coś źle robię, bo kolejność testów jest inna
        Subject subject = new Subject();
        subject.setSubjectName("_intive");
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);

        subject.setSubjectName("_InTiVe");
        response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/signup", subject, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void emptyReservationShouldFail() {
        Reservation reservation = new Reservation();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/reservations", reservation, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void reservingNullSpaceShouldFail() {
        Reservation reservation = new Reservation();
        reservation.setSubjectId(1);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/reservations", reservation, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void reservingByNullSubjectShouldFail() {
        Reservation reservation = new Reservation();
        reservation.setSpaceId(1);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/reservations", reservation, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void reservingNonExistingSpaceShouldFail() {
        Reservation reservation = new Reservation();
        reservation.setSpaceId(Integer.MIN_VALUE);
        reservation.setSubjectId(1);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/reservations", reservation, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void reservingExistingParkingSpaceByNonExistingSubjectShouldFail() {
        Reservation reservation = new Reservation();
        reservation.setSpaceId(1);
        reservation.setSubjectId(Integer.MIN_VALUE);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/carpark/reservations", reservation, String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
    }
}
