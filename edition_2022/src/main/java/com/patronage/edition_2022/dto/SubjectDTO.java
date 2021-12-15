/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patronage.edition_2022.dto;

import com.patronage.edition_2022.model.Subject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author jklimaszewski
 */
public class SubjectDTO {
    @NotBlank(message = "Nazwa nie może być pusta!")
    @Size(min = 2, max = 20, message = "Nazwa musi mieć długość od 2 do 20 znaków!")
    @Pattern(regexp = "\\w+(\\s\\w+)*", message = "Nazwa może składać się wyłącznie z liter alfabetu łacińskiego, cyfr, znaku podkreślenia, a pomiędzy wyrazami może być wyłącznie pojedynczy odstęp!")
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public static SubjectDTO makeFromSubject(Subject subject) {
        SubjectDTO subjectDto = new SubjectDTO();
        subjectDto.setSubjectName(subject.getSubjectName());
        return subjectDto;
    }
}
