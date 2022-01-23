package com.personapi.service;

import com.personapi.dto.request.PersonDTO;
import com.personapi.entity.Person;
import com.personapi.exception.PersonNotFoundException;
import com.personapi.mapper.PersonMapper;
import com.personapi.repository.PersonRepository;
import com.personapi.utils.PersonUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @InjectMocks
    private PersonService underTest;

    @Test
    void shouldReturnAllPeople(){
        List<PersonDTO> expectedPersonList = Collections.singletonList(PersonUtils.createFakeDTOResponse());

        Mockito.when(personRepository.findAll()).thenReturn(Collections.singletonList(PersonUtils.createFakeEntity()));

        List<PersonDTO> returnedPersonList = underTest.listAll();

        Assertions.assertThat(returnedPersonList).isEqualTo(expectedPersonList);
    }

    @Test
    void givenPersonIdReturnPerson() throws PersonNotFoundException{
        //given
        Long personId = 1L;
        PersonDTO expectedPerson = PersonUtils.createFakeDTOResponse();

        //when
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.of(PersonUtils.createFakeEntity()));
        PersonDTO returnedPerson = underTest.findById(personId);

        //then
        Assertions.assertThat(returnedPerson).isEqualTo(expectedPerson);
    }

    @Test
    void givenPersonIdWillThrowPersonNotFoundException(){
        //given
        Long personId = 999L;

        //when
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.empty());

        //then
        Assertions.assertThatThrownBy(() -> underTest.findById(personId))
                .isInstanceOf(PersonNotFoundException.class)
                .hasMessage("Person not found with id: " + personId);
    }

    @Test
    void givenPersonDTOReturnPersonSaved(){
        //given
        PersonDTO personDTO = PersonUtils.createFakeDTORequest();
        Person expectedSavedPerson = PersonUtils.createFakeEntity();
        PersonDTO expectedPersonDTO = PersonUtils.createFakeDTOResponse();

        //when
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(expectedSavedPerson);


        PersonDTO returnedPerson = underTest.createPerson(personDTO);

        //then
        Assertions.assertThat(returnedPerson).isEqualTo(expectedPersonDTO);
    }

    @Test
    void givenPersonIdAndPersonDTOShouldReturnPersonDTOOnUpdate() throws PersonNotFoundException{
        //given
        Long personId = 1L;
        PersonDTO personDTO = PersonUtils.createFakeDTOResponse();

        //when
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.of(PersonUtils.createFakeEntity()));
        Mockito.when(personRepository.save(personMapper.toModel(personDTO))).thenReturn(PersonUtils.createFakeEntity());
        PersonDTO updatedPerson = underTest.updatePerson(personId, personDTO);

        //then
        Assertions.assertThat(updatedPerson).isEqualTo(personDTO);
    }

    @Test
    void givenPersonIdAndPersonDTOWillThrowPersonNotFindExceptionOnUpdate(){
        //given
        Long personId = 1L;
        PersonDTO personDTO = PersonUtils.createFakeDTORequest();

        //when
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.empty());
        //then
        Assertions.assertThatThrownBy(() -> underTest.updatePerson(personId, personDTO))
                .isInstanceOf(PersonNotFoundException.class)
                .hasMessage("Person not found with id: " + personId);

        Mockito.verify(personRepository, Mockito.never()).save(ArgumentMatchers.any());
    }

    @Test
    void givenPersonIdShouldDeletePersonAndReturnPersonDTO() throws PersonNotFoundException{
        //given
        Long personId = 1L;
        PersonDTO personDeleted = PersonUtils.createFakeDTOResponse();

        //when
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.of(PersonUtils.createFakeEntity()));
        PersonDTO returnedPerson = underTest.deletePerson(personId);

        //then
        Assertions.assertThat(returnedPerson).isEqualTo(personDeleted);
    }

    @Test
    void givenPersonIdWillThrowPersonNotFindOnDelete(){
        //given
        Long personId = 1L;

        //when
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.empty());

        //then
        Assertions.assertThatThrownBy(() -> underTest.deletePerson(personId))
                .isInstanceOf(PersonNotFoundException.class)
                .hasMessage("Person not found with id: " + personId);

        Mockito.verify(personRepository, Mockito.never()).deleteById(personId);
    }

}
