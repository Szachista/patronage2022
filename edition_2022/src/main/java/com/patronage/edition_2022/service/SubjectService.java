/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patronage.edition_2022.service;

import com.patronage.edition_2022.dto.SubjectDTO;
import com.patronage.edition_2022.repository.SubjectRepository;
import com.patronage.edition_2022.model.Subject;
import javax.validation.Valid;
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
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepo;

    public boolean isSubjectExisting(@NonNull Integer subjectId) {
        return subjectRepo.findBySubjectId(subjectId) != null;
    }

    public Subject createSubject(String subjectName) {
//        if (subjectName.isEmpty() || subjectName.isBlank())
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brak nazwy podmiotu!");
//
//        if (subjectName.length() < 2 || subjectName.length() > 20)
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nazwa musi mieć długość od 2 do 20 znaków!");
//
//        if (!subjectName.matches("\\w+(\\s\\w+)*"))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, subjectName + ": Nazwa może składać się wyłącznie z liter alfabetu łacińskiego, cyfr, znaku podkreślenia, a pomiędzy wyrazami może być wyłącznie pojedynczy odstęp!");

        Subject found = subjectRepo.findBySubjectNameIgnoreCase(subjectName);
        if (found != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nazwa zajęta! Wybierz inną!");

        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        return subjectRepo.save(subject);
    }
}
