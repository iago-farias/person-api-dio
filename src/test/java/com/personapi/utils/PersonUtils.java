package com.personapi.utils;

import com.personapi.dto.request.PersonDTO;
import com.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {
    private static final String FIRST_NAME = "João";
    private static final String LAST_NAME = "da Silva";
    private static final String CPF_NUMBER = "369.333.878-79";
    private static final long PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(2010, 10, 1);

    public static PersonDTO createFakeDTORequest() {
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate("01-10-2010")
                .phones(Collections.singletonList(PhoneUtils.createFakeDTORequest()))
                .build();
    }

    public static PersonDTO createFakeDTOResponse() {
        return PersonDTO.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate("01-10-2010")
                .phones(Collections.singletonList(PhoneUtils.createFakeDTOResponse()))
                .build();
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }
}
