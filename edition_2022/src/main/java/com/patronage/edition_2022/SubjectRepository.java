/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.patronage.edition_2022;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jklimaszewski
 */
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    public Subject findBySubjectId(Integer subjectId);
}
