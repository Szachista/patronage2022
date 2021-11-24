# patronage2022

Zadanie rekrutacyjne do Patronage 2022. Wymagania:

- Java 11
- Gradle 7.2
- Spring Boot v2.6.0

## Opis aplikacji

Aplikacja umożliwia rezerwację miejsc parkingowych w parkingowcu, zgodnie z przysłanymi wymaganiami.

## Jak zbudować aplikację?

W systemie Linux użyj polecenia:

> ./gradlew build

## Jak uruchomić aplikację?

W systemie Linux użyj polecenia:

> ./gradlew bootRun

## Przykłady wysyłania żądań za pomocą polecenia curl

Chcąc dodać nowy podmiot o nazwie "podmiot" należy wywołać polecenie:

> curl -X POST -H "Content-Type: application/json" -d '{"subjectName": "podmiot"}' localhost:8080/carpark/signup

Chcąc dodać rezerwację dla podmiotu o identyfikatorze 3 i miejsca parkingowego o identyfikatorze 5 należy wywołać polecenie:

> curl -X POST -H "Content-Type: application/json" -d '{"spaceId": 5, "subjectId": 3}' localhost:8080/carpark/reservations

Chcąc usunąć rezerwację dla podmiotu o identyfikatorze 3 i miejsca parkingowego o identyfikatorze 5 należy wywołać polecenie:

> curl -X DELETE -H "Content-Type: application/json" -d '{"spaceId": 5, "subjectId": 3}' localhost:8080/carpark/reservations

Chcąc pobrać listę wolnych miejsc należy wywołać polecenie:

> curl -X GET localhost:8080/carpark/emptyspaces

Chcąc pobrać listę rezerwacji dla podmiotu o identyfikatorze 1 należy wykonać polecenie:

> curl -X GET localhost:8080/carpark/reservations/1
